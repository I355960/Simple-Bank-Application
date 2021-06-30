package com.finalproject.bank.Repositatory;

import com.finalproject.bank.Entity.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bankRepo extends CrudRepository<Bank,String> {
}
