package com.macquarie.banking.account.service;

import com.macquarie.banking.account.DAO.AccountDAO;
import com.macquarie.banking.account.DTO.AccountDto;
import com.macquarie.banking.account.DTO.AccountResponse;
import com.macquarie.banking.account.exceptions.NoAccountFoundException;
import com.macquarie.banking.account.service.AccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class AccountServiceTest {
    @MockitoBean
    private AccountDAO accountDAO;
    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("should return list of accounts")
    void test1(){
        //given
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountName("test123");
        accountDto.setAccountNumber(123456123l);
        accountDto.setCurrency("INR");
        //when
        when(accountDAO.fetchAllAccounts()).thenReturn(List.of(accountDto));
        //then
        List<AccountResponse> accountResponses= accountService.fetchAllAccounts();
        //Assertions
        assertEquals(1,accountResponses.size());
        //verify
        verify(accountDAO).fetchAllAccounts();
    }

    @Test
    @DisplayName("should give empty list when no account found")
    void test2(){
        //when
        when(accountDAO.fetchAllAccounts()).thenReturn(new ArrayList<>());
        //then
        List<AccountResponse> accountResponses= accountService.fetchAllAccounts();
        //Assertions
        assertEquals(0,accountResponses.size());
    }

}
