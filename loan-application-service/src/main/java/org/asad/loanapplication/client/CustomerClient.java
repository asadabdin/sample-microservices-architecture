package org.asad.loanapplication.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.loanapplication.exception.NotFoundException;
import org.asad.loanapplication.model.CustomerDTO;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerClient {

    private final CustomerApi customerApi;


    public CustomerDTO getCustomerById(String id) {
        return ofNullable(id)
                .map(customerApi::getCustomerById)
                .orElseThrow(() -> new NotFoundException("No such Customer Found"));
    }
}
