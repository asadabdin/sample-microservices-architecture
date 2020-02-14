package org.asad.loanapplication.client;

import org.asad.loanapplication.model.CustomerDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer-service")
interface CustomerApi {

    @Cacheable(cacheNames = "customer-id")
    @GetMapping("/api/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable(name = "id") String id);
}
