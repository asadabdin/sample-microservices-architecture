package org.asad.customer.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class CreateCustomerResponse {

    @NotBlank
    private String id;
}
