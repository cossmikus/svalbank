package com.example.bank.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String accountNumber, long balance, long requested) {
        super("Account %s has %d, cannot withdraw %d".formatted(accountNumber, balance, requested));
    }
}