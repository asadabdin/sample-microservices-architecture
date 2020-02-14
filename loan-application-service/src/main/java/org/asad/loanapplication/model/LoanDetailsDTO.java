package org.asad.loanapplication.model;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class LoanDetailsDTO {

    @NotNull
    private CustomerDTO customer;
    @NotEmpty
    private List<LoanDTO> loans;
}
