package org.asad.loanapplication.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class RegistrationResponse {

    @NotBlank
    private String id;
}
