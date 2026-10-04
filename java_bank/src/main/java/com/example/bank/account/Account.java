package com.example.bank.account;


import com.example.bank.exceptions.InsufficientFundsException;
import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;

    @Column(name="balance")
    private Long balance;

    protected Account() {   // JPA requires a no-arg constructor
    }

    public Account(String accountNumber, String firstName, String lastName) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = 0L;
    }
    public static Account open(String accountNumber, String firstName, String lastName) {
        return new Account(accountNumber, firstName, lastName);
    }

    public void credit(long amountMinor) {
        requirePositive(amountMinor);
        this.balance = Math.addExact(this.balance, amountMinor);
    }

    public void debit(long amountMinor) {
        requirePositive(amountMinor);
        if (amountMinor > this.balance) {
            throw new InsufficientFundsException(accountNumber, balance, amountMinor);
        }
        this.balance -= amountMinor;
    }

    private void requirePositive(long amountMinor) {
        if (amountMinor <= 0) {
            throw new IllegalArgumentException("Amount must be positive: " + amountMinor);
        }
    }

    public Long getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public long getBalance() { return balance; }
}
