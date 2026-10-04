package com.example.bank.account;

import com.example.bank.account.dto.AccountResponse;
import com.example.bank.account.dto.AmountRequest;
import com.example.bank.account.dto.OpenAccountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponse open(@Valid @RequestBody OpenAccountRequest request){
        return accountService.open(request);
    }

    @GetMapping("/{accountNumber}")
    AccountResponse findByAccountNumber(@PathVariable String accountNumber){
        return accountService.findByAccountNumber(accountNumber);
    }

    @GetMapping
    List<AccountResponse> findByLastName(@RequestParam String lastName){
        return accountService.findByLastName(lastName);
    }

    @PostMapping("/{accountNumber}/deposit")
    AccountResponse deposit(@PathVariable String accountNumber,
                            @Valid @RequestBody AmountRequest request){
        return accountService.deposit(accountNumber, request.amount());
    }

    @PostMapping("/{accountNumber}/withdrawals")
    AccountResponse withdrawal(@PathVariable String accountNumber,
                             @Valid @RequestBody AmountRequest request){
        return accountService.withdraw(accountNumber, request.amount());
    }


}

