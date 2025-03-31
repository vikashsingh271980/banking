package com.macquarie.banking.transaction.service;

import com.macquarie.banking.account.DTO.AccountResponse;
import com.macquarie.banking.transaction.DTO.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> fetchAllTransactions(Long accountNo);
}
