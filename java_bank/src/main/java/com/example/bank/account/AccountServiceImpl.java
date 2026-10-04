package com.example.bank.account;


import com.example.bank.account.dto.AccountResponse;
import com.example.bank.account.dto.OpenAccountRequest;
import com.example.bank.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.security.SecureRandom;
import java.util.List;


//public interface AccountService {
//    double getBalance();
//    void deposit(double amount);
//    void withdraw(double amount);
//}

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private static final SecureRandom RANDOM = new SecureRandom();

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse open(OpenAccountRequest request){
        String accountNumber = String.format("%018d",
                RANDOM.nextLong(1_000_000_000_000_000_000L));

        var account = accountRepository.save(Account.open(
                accountNumber,
                request.firstName(),
                request.lastName()
        ));

        return AccountResponse.from(account);

    }

    @Override
    public AccountResponse findByAccountNumber(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        return AccountResponse.from(account);
    }

    @Override
    public List<AccountResponse> findByLastName(String lastName){
        return accountRepository.findByLastName(lastName).stream().map(AccountResponse::from).toList();
    }
    public AccountResponse deposit(String accountNumber, long amount){
        Account account = accountRepository.findForUpdateByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.credit(amount);
        accountRepository.save(account);
        return AccountResponse.from(account);
    }
    public AccountResponse withdraw(String accountNumber, long amount){
        Account account = accountRepository.findForUpdateByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        account.debit(amount);
        accountRepository.save(account);
        return AccountResponse.from(account);
    }

}
