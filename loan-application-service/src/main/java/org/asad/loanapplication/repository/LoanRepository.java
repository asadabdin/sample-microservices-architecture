package org.asad.loanapplication.repository;

import org.asad.loanapplication.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, String> {

    List<Loan> getByCustomerId(Integer customerId);
}
