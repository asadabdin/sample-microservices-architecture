package org.asad.loanapplication.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rossillo.spring.web.mvc.CacheControl;
import org.asad.loanapplication.model.LoanDetailsDTO;
import org.asad.loanapplication.model.RegistrationRequest;
import org.asad.loanapplication.model.RegistrationResponse;
import org.asad.loanapplication.service.LoanService;
import org.asad.loanapplication.validators.RegistrationRequestValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.rossillo.spring.web.mvc.CachePolicy.MUST_REVALIDATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loanapplications")
public class LoanController {

    private final LoanService loanService;
    private final RegistrationRequestValidator registrationRequestValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationRequestValidator);
    }

    @ResponseStatus(CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public RegistrationResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        log.info("register loan application with {}", registrationRequest);
        return loanService.registerLoanRequest(registrationRequest);
    }

    @CacheControl(maxAge = 10, policy = MUST_REVALIDATE)
    @GetMapping(params = "customer-id", produces = APPLICATION_JSON_VALUE)
    public LoanDetailsDTO getByCustomerId(@RequestParam("customer-id") Integer customerId) {
        log.info("Looking for loan by Customer Id : {}", customerId);
        return loanService.getByCustomerId(customerId);
    }

}
