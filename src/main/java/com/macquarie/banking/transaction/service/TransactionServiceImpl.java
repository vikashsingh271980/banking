package com.macquarie.banking.transaction.service;

import com.macquarie.banking.transaction.DAO.TransactionDAO;
import com.macquarie.banking.transaction.DTO.TransactionResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final ModelMapper modelMapper;
    private final TransactionDAO transactionDAO;

    @Override
    public List<TransactionResponse> fetchAllTransactions(Long accountNo) {
        return transactionDAO.fetchAllTransactions(accountNo).stream().map(transactionDto -> {
           TransactionResponse transactionResponse =  modelMapper.map(transactionDto, TransactionResponse.class);
           transactionResponse.setValueDate(transactionDto.getValueDate().format(DateTimeFormatter.ofPattern("MMM. d,uuuu")));
           return transactionResponse;
        }).toList();
    }
}
