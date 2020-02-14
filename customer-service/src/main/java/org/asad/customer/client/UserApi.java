package org.asad.customer.client;

import org.asad.customer.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("user-service")
public interface UserApi {

    @GetMapping(path = "/api/users/{id}", produces = APPLICATION_JSON_VALUE)
    UserDTO getById(@PathVariable("id") String id);
}
