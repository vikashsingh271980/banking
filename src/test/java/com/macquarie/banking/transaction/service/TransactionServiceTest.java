package com.macquarie.banking.transaction.service;

;
import com.macquarie.banking.transaction.DAO.TransactionDAO;
import com.macquarie.banking.transaction.DTO.TransactionDto;
import com.macquarie.banking.transaction.DTO.TransactionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class TransactionServiceTest {
    @MockitoBean
    private TransactionDAO transactionDAO;
    @Autowired
    private TransactionService transactionService;

    @Test
    @DisplayName("should return list of transactions")
    void test1(){
        //given
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountName("test123");
        transactionDto.setAccountNumber(123456123l);
        transactionDto.setCurrency("INR");
        transactionDto.setValueDate(LocalDate.now());
        //when
        when(transactionDAO.fetchAllTransactions(anyLong())).thenReturn(List.of(transactionDto));
        //then
        List<TransactionResponse> transactionResponses= transactionService.fetchAllTransactions(1L);
        //Assertions
        assertEquals(1,transactionResponses.size());
        //verify
        verify(transactionDAO).fetchAllTransactions(anyLong());
    }

    @Test
    @DisplayName("should give empty list when no transaction found")
    void test2(){
        //when
        when(transactionDAO.fetchAllTransactions(anyLong())).thenReturn(new ArrayList<>());
        //then
        List<TransactionResponse> transactionResponses= transactionService.fetchAllTransactions(anyLong());
        //Assertions
        assertEquals(0,transactionResponses.size());
    }

}
