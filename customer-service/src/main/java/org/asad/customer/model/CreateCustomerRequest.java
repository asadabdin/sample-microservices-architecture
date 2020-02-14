package org.asad.customer.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCustomerRequest {

    @NotNull
    private String userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String phone;
    private String email;

}
