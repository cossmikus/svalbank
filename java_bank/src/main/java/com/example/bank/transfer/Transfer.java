package com.example.bank.transfer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_account_id")
    private Integer fromAccount;

    @Column(name = "to_account_id")
    private Integer toAccount;

    @Column(name = "amount")
    @Positive
    private Integer amount;

    @Column(name = "status")
    private String status;


}
