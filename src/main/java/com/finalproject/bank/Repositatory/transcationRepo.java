package com.finalproject.bank.Repositatory;



import com.finalproject.bank.Entity.Transcation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface transcationRepo extends CrudRepository<Transcation,String> {

    List<Transcation> findBytransDateBetween(LocalDate date1,LocalDate date2);
}
