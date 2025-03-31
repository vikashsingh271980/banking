package com.macquarie.banking.transaction.DAO;

import com.macquarie.banking.account.entity.AccountEntity;
import com.macquarie.banking.account.exceptions.NoAccountFoundException;
import com.macquarie.banking.transaction.DTO.TransactionDto;
import com.macquarie.banking.transaction.entity.TransactionEntity;
import com.macquarie.banking.transaction.exceptions.NoTransactionFoundException;
import com.macquarie.banking.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Repository
@AllArgsConstructor
public class TransactionDAOImpl implements TransactionDAO {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<TransactionDto> fetchAllTransactions(Long accountNo) {
        List<TransactionEntity> transactions = transactionRepository.findByAccount(accountNo);
        if(isEmpty(transactions)){
            throw new NoTransactionFoundException();
        }
        return transactions.stream().map(transactionEntity -> {
          TransactionDto dto =  modelMapper.map(transactionEntity, TransactionDto.class);
          dto.setAccountNumber(transactionEntity.getAccount().getAccountNumber());
          dto.setValueDate(transactionEntity.getValueDate().toLocalDate());
          return dto;
        }
        ).toList();
    }
}
