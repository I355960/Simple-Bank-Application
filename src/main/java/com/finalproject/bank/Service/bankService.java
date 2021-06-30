package com.finalproject.bank.Service;

import com.finalproject.bank.Entity.Account;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Repositatory.accountRepo;
import com.finalproject.bank.Repositatory.bankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Component
public class bankService {

    @Autowired
    private bankRepo bankrepo;
    @Autowired
    private accountRepo accountrepo;
    @Autowired
    private Account account;
    @Autowired
    private Bank bank;


    public Bank createBank( Bank bank)
    {

        bank.setBankCode(bank.getBankCode());
        bank.setBankName(bank.getBankName());
        bank.setAddress(bank.getAddress());
        bank.setPinCode(bank.getPinCode());
        bankrepo.save(bank);
        return bank;
    }

    public List<Bank> getAllBank()
    {
        List<Bank> bank = new ArrayList<Bank>();
        bankrepo.findAll().forEach(bank1 -> bank.add(bank1));
        return bank;
    }

    public String deleteCustomer(String accountNo)
    {
       accountrepo.deleteById(accountNo);
        return "Delete customer record" +accountNo;
    }

    public String recordUpdate(Account account1, String id)
    {
        account = accountrepo.findById(id).get();
        if(account.getAccountNo() == id)
        {
            account.setCustomerName(account1.getCustomerName());
            account.setCustomerAddress(account1.getCustomerAddress());
            account.setPinCode(account1.getPinCode());
            account.setPanCard(account1.getPanCard());
            accountrepo.save(account);
            return "Customer id " +id +"record is updated";
        }
        return "Record not found";

    }

    public Account getCustomerById(String id)
    {

        account = accountrepo.findById(id).get();
        return account;
    }


}
