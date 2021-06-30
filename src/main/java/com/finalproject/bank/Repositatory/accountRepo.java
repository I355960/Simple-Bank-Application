package com.finalproject.bank.Repositatory;

import com.finalproject.bank.Entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountRepo extends CrudRepository<Account,String> {
}
