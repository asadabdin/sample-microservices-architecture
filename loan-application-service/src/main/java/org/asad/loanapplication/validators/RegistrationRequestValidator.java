package org.asad.loanapplication.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.loanapplication.client.CustomerClient;
import org.asad.loanapplication.model.RegistrationRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationRequestValidator implements Validator {

    private final CustomerClient customerClient;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationRequest request = (RegistrationRequest) o;
        try {
            customerClient.getCustomerById(request.getCustomerId());
        } catch (Exception e) {
            errors.rejectValue("customerId", "customerId.invalid", e.getMessage());
        }
    }
}
