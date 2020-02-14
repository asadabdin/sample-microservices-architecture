package org.asad.loanapplication.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
}
