package org.asad.customer.service;

import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.asad.customer.domain.Customer;
import org.asad.customer.exception.NotFoundException;
import org.asad.customer.model.CreateCustomerRequest;
import org.asad.customer.model.CreateCustomerResponse;
import org.asad.customer.model.CustomerDTO;
import org.asad.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final MapperFacade mapperFacade;
    private final CustomerRepository customerRepository;

    @SneakyThrows
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        return ofNullable(createCustomerRequest)
                .map(request -> mapperFacade.map(request, Customer.class))
                .map(customerRepository::save)
                .map(loan -> mapperFacade.map(loan, CreateCustomerResponse.class))
                .orElseThrow(BadHttpRequest::new);
    }

    public CustomerDTO getByCustomerId(Integer customerId) {
        return ofNullable(customerId)
                .flatMap(customerRepository::getById)
                .map(customer -> mapperFacade.map(customer, CustomerDTO.class))
                .orElseThrow(() -> new NotFoundException("No Such Customer found against Customer id {}" + customerId));
    }

}
