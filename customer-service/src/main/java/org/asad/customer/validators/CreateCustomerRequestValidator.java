package org.asad.customer.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.customer.client.UserClient;
import org.asad.customer.model.CreateCustomerRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateCustomerRequestValidator implements Validator {

    private final UserClient userClient;

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateCustomerRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateCustomerRequest request = (CreateCustomerRequest) o;
        try {
            userClient.getUserById(request.getUserId());
        } catch (Exception e) {
            errors.rejectValue("userId", "userId.invalid", e.getMessage());
        }
    }
}
