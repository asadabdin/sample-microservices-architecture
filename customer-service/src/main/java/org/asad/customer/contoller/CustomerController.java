package org.asad.customer.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.customer.model.CreateCustomerRequest;
import org.asad.customer.model.CreateCustomerResponse;
import org.asad.customer.model.CustomerDTO;
import org.asad.customer.service.CustomerService;
import org.asad.customer.validators.CreateCustomerRequestValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CreateCustomerRequestValidator createCustomerRequestValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(createCustomerRequestValidator);
    }

    @ResponseStatus(CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public CreateCustomerResponse create(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        log.info("Create customer with {}", createCustomerRequest);
        return customerService.createCustomer(createCustomerRequest);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public CustomerDTO getByCustomerId(@PathVariable("id") Integer id) {
        log.info("Looking for Customer by Customer Id : {}", id);
        return customerService.getByCustomerId(id);
    }

}
