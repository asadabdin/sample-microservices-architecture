package org.asad.customer.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends CreateCustomerRequest {
    private Integer id;
}
