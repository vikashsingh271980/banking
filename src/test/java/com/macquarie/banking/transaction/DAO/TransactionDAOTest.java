package com.macquarie.banking.transaction.DAO;

import com.macquarie.banking.account.entity.AccountEntity;
import com.macquarie.banking.transaction.DTO.TransactionDto;
import com.macquarie.banking.transaction.entity.TransactionEntity;
import com.macquarie.banking.transaction.exceptions.NoTransactionFoundException;
import com.macquarie.banking.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionDAOTest {
    @MockitoBean private TransactionRepository transactionRepository;
    @Autowired private TransactionDAO transactionDAO;

    @Test
    @DisplayName("should return list of transactions")
    void test1(){
        //given
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAccountName("test123");
        transactionEntity.setAccount(new AccountEntity());
        transactionEntity.setCurrency("INR");
        transactionEntity.setValueDate(LocalDateTime.now());
        //when
        when(transactionRepository.findByAccount(anyLong())).thenReturn(List.of(transactionEntity));
        //then
        List<TransactionDto> transactionDtos= transactionDAO.fetchAllTransactions(1L);
        //Assertions
        assertEquals(1,transactionDtos.size());
        //verify
        verify(transactionRepository).findByAccount(1L);
    }

    @Test
    @DisplayName("should give exception when no transaction found")
    void test2(){
        //when
        when(transactionRepository.findByAccount(anyLong())).thenReturn(new ArrayList<>());
        //then
        NoTransactionFoundException exception= assertThrows(NoTransactionFoundException.class, () -> transactionDAO.fetchAllTransactions(1L));
        //Assertions
        assertTrue(exception != null);
    }
}
