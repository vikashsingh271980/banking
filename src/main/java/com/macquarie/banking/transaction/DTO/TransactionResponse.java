package com.macquarie.banking.transaction.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long accountNumber;
    private String accountName;
    private String valueDate;
    private String currency;
    private BigDecimal transactionAmount;
    private String transactionType;
    private String transactionNarrative;
}
