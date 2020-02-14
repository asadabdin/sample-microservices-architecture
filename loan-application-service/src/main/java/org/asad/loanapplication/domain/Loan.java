package org.asad.loanapplication.domain;

import lombok.Data;
import org.asad.loanapplication.model.LoanStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;
    private Integer duration;

    @Type(type = "h2-enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('CREATED', 'DENIED', 'APPLIED')", nullable = false)
    private LoanStatus status;
    private Integer customerId;
}
