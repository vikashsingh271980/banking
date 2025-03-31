package com.macquarie.banking.account.service;

import com.macquarie.banking.account.DAO.AccountDAO;
import com.macquarie.banking.account.DTO.AccountResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final ModelMapper modelMapper;
    private final AccountDAO accountDAO;
    @Override
    public List<AccountResponse> fetchAllAccounts() {
        return accountDAO.fetchAllAccounts().stream().map(accountDto -> modelMapper.map(accountDto, AccountResponse.class)).toList();
    }
}
