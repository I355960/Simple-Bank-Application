package com.finalproject.bank.Repositatory;

import com.finalproject.bank.Entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface accountRepo extends CrudRepository<Account,String> {


    List<Account> findBycustomerNameContaining(String customerName);
    List<Account> findBypinCode(String pinCode);

}
