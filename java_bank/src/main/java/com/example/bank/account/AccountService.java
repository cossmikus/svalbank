package com.example.bank.account;


import com.example.bank.account.dto.AccountResponse;
import com.example.bank.account.dto.OpenAccountRequest;

import java.util.List;

public interface AccountService {
    AccountResponse open(OpenAccountRequest request);
    AccountResponse findByAccountNumber(String accountNumber);
    List<AccountResponse> findByLastName(String lastName);
    AccountResponse deposit(String accountNumber, long amountMinor);
    AccountResponse withdraw(String accountNumber, long amountMinor);
}
