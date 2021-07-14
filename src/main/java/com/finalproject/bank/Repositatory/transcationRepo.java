package com.finalproject.bank.Repositatory;



import com.finalproject.bank.Entity.Transcation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface transcationRepo extends CrudRepository<Transcation,String> {

    List<Transcation> findBytransDateBetween(LocalDate date1,LocalDate date2);

    @Query(
            value = "SELECT * FROM Transcation T WHERE T.ACCOUNT_NO = ?1 and T.TRANS_DATE   >= ?2 and T.TRANS_DATE   <= ?3",
            nativeQuery = true)
    List<Transcation> findAllAccountIdBetweenDate(String ACCOUNT_NO ,LocalDate TRANS_DATE,LocalDate TRANS_DATE_CLOSE);

}
