package com.macquarie.banking.transaction.DAO;

import com.macquarie.banking.transaction.DTO.TransactionDto;

import java.util.List;

public interface TransactionDAO {
    List<TransactionDto> fetchAllTransactions(Long accountNo);

}
