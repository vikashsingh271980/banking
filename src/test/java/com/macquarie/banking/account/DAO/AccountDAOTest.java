package com.macquarie.banking.account.DAO;

import com.macquarie.banking.account.DTO.AccountDto;
import com.macquarie.banking.account.DTO.AccountResponse;
import com.macquarie.banking.account.entity.AccountEntity;
import com.macquarie.banking.account.exceptions.NoAccountFoundException;
import com.macquarie.banking.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountDAOTest {
    @MockitoBean private AccountRepository accountRepository;
    @Autowired private AccountDAO accountDAO;

    @Test
    @DisplayName("should return list of accounts")
    void test1(){
        //given
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName("test123");
        accountEntity.setAccountNumber(123456123l);
        accountEntity.setCurrency("INR");
        //when
        when(accountRepository.findAll()).thenReturn(List.of(accountEntity));
        //then
        List<AccountDto> accounts= accountDAO.fetchAllAccounts();
        //Assertions
        assertEquals(1,accounts.size());
        //verify
        verify(accountRepository).findAll();
    }

    @Test
    @DisplayName("should give exception when no account found")
    void test2(){
        //when
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());
        //then
        NoAccountFoundException accountResponses= assertThrows(NoAccountFoundException.class, () -> accountDAO.fetchAllAccounts());
        //Assertions
        assertTrue(accountResponses != null);
    }
}
