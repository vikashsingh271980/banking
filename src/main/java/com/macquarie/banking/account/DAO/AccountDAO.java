package com.macquarie.banking.account.DAO;

import com.macquarie.banking.account.DTO.AccountDto;
import com.macquarie.banking.account.DTO.AccountResponse;

import java.util.List;

public interface AccountDAO {
    List<AccountDto> fetchAllAccounts();

}
