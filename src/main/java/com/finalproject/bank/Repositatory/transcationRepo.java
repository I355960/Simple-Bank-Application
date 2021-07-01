package com.finalproject.bank.Repositatory;


import com.finalproject.bank.Entity.Transcation;
import org.springframework.data.repository.CrudRepository;

public interface transcationRepo extends CrudRepository<Transcation,String> {
}
