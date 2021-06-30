package com.finalproject.bank.Repositatory;


import com.finalproject.bank.Entity.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface branchRepo extends CrudRepository<Branch,String> {
}
