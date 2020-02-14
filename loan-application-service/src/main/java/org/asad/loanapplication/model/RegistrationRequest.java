package org.asad.loanapplication.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationRequest {

    @NotBlank
    private String customerId;
    @NotNull
    private Double amount;
    @NotNull
    private Integer duration;

}
