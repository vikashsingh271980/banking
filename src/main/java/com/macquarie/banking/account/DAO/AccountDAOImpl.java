package com.macquarie.banking.account.DAO;

import com.macquarie.banking.account.DTO.AccountDto;
import com.macquarie.banking.account.exceptions.NoAccountFoundException;
import com.macquarie.banking.account.repository.AccountRepository;
import com.macquarie.banking.account.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Repository
@AllArgsConstructor
public class AccountDAOImpl implements AccountDAO{
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<AccountDto> fetchAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        if(isEmpty(accounts)){
            throw new NoAccountFoundException();
        }
        return accounts.stream().map(accountEntity -> modelMapper.map(accountEntity, AccountDto.class)).toList();
    }
}
