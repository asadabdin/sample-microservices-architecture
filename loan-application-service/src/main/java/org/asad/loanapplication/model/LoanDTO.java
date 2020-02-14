package org.asad.loanapplication.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoanDTO {
    private Integer id;
    @NotNull
    private Double amount;
    @NotNull
    private Integer duration;
    private LoanStatus status;
}
