package com.example.bank.account.dto;

import com.example.bank.account.Account;

public record AccountResponse(Long id, String accountNumber, String firstName, String lastName, long balance) {
    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getFirstName(),
                account.getLastName(),
                account.getBalance()
        );
    }
}
