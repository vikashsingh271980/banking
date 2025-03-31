package com.macquarie.banking.account.service;

import com.macquarie.banking.account.DTO.AccountResponse;
import java.util.List;

public interface AccountService {
    List<AccountResponse> fetchAllAccounts();
}
