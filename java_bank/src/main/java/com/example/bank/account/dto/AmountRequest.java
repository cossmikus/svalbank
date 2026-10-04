package com.example.bank.account.dto;

import jakarta.validation.constraints.Positive;

public record AmountRequest(@Positive long amount) {
}
