package org.asad.loanapplication.service;

import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections.CollectionUtils;
import org.asad.loanapplication.client.CustomerClient;
import org.asad.loanapplication.domain.Loan;
import org.asad.loanapplication.exception.NotFoundException;
import org.asad.loanapplication.model.*;
import org.asad.loanapplication.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanService {

    private final MapperFacade mapperFacade;
    private final CustomerClient customerClient;
    private final LoanRepository loanRepository;

    @SneakyThrows
    public RegistrationResponse registerLoanRequest(RegistrationRequest registrationRequest) {
        return ofNullable(registrationRequest)
                .map(request -> mapperFacade.map(request, Loan.class))
                .map(loanRepository::save)
                .map(loan -> mapperFacade.map(loan, RegistrationResponse.class))
                .orElseThrow(BadHttpRequest::new);
    }

    public LoanDetailsDTO getByCustomerId(Integer customerId) {
        return ofNullable(customerId)
                .map(loanRepository::getByCustomerId)
                .filter(CollectionUtils::isNotEmpty)
                .map(loans -> mapperFacade.mapAsList(loans, LoanDTO.class))
                .map(loanDTOS -> populateCustomerDto(customerId, loanDTOS))
                .orElseThrow(() -> new NotFoundException("No Such Loans found against Customer id {}" + customerId));
    }

    private LoanDetailsDTO populateCustomerDto(Integer customerId, List<LoanDTO> loanDTOS) {
        log.debug("Mapping LoanDetailsDto with {} and {}", customerId, loanDTOS);
        CustomerDTO customerDTO = customerClient.getCustomerById(String.valueOf(customerId));
        log.debug("customer found: {}", customerDTO);
        return new LoanDetailsDTO(customerDTO, loanDTOS);
    }

}
