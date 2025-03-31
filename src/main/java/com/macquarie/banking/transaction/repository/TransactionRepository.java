package com.macquarie.banking.transaction.repository;

import com.macquarie.banking.account.entity.AccountEntity;
import com.macquarie.banking.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "select * from transaction where account_number=:accountNo", nativeQuery = true)
    List<TransactionEntity> findByAccount(@Param("accountNo") Long accountNo);

}
