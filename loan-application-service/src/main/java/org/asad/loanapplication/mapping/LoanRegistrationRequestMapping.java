package org.asad.loanapplication.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.asad.loanapplication.domain.Loan;
import org.asad.loanapplication.model.RegistrationRequest;
import org.springframework.stereotype.Component;

import static org.asad.loanapplication.model.LoanStatus.CREATED;

@Component
public class LoanRegistrationRequestMapping implements ObjectMapping<Loan, RegistrationRequest> {

    @Override
    public void classMap(ClassMapBuilder<Loan, RegistrationRequest> classMapBuilder) {
        classMapBuilder
                .byDefault()
                .customize(new CustomMapper<Loan, RegistrationRequest>() {
                    @Override
                    public void mapBtoA(RegistrationRequest registrationRequest, Loan loan, MappingContext context) {
                        loan.setStatus(CREATED);
                    }
                });

    }
}

