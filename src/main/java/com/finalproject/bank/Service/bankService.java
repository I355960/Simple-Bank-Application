package com.finalproject.bank.Service;

import com.finalproject.bank.Entity.Account;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Entity.Branch;
import com.finalproject.bank.Entity.Transcation;
import com.finalproject.bank.Repositatory.accountRepo;
import com.finalproject.bank.Repositatory.bankRepo;
import com.finalproject.bank.Repositatory.transcationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
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
    @Autowired
    private transcationRepo transcationrepo;


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


    public String getCreditAmount(Transcation transc,String id)
    {
        account = accountrepo.findById(id).get();
        Transcation transcation = new Transcation();
        transcation.setCreditBalance(transc.getCreditBalance());
        transcation.setRemarks(transc.getRemarks());
        transcation.setAccount(account);
        account.setBalance(account.getBalance()+transc.getCreditBalance());
        accountrepo.save(account);
        transcationrepo.save(transcation);

        return +transc.getCreditBalance() +" is credited in Account number" +id +" . Your total balance is " +account.getBalance();

    }



    public String getDebitAmount(Transcation transc,String id)
    {
        account = accountrepo.findById(id).get();
        if(account.getBalance()>transc.getDebitBalance()) {
            Transcation transcation = new Transcation();
            transcation.setDebitBalance(transc.getDebitBalance());
            transcation.setRemarks(transc.getRemarks());
            transcation.setAccount(account);
            account.setBalance(account.getBalance() - transc.getDebitBalance());
            accountrepo.save(account);
            transcationrepo.save(transcation);

            return +transc.getCreditBalance() + " is debited in Account number " + id + ".Your total balance is " + account.getBalance();
        }
        return "Insufficent balance in your account number " +id;

    }


    public List<Account> getCustomerByName(String name)
    {

        List<Account> account = accountrepo.findBycustomerNameContaining(name);
        return account;
    }

    public List<Account> getCustomerByPincode(String pincode)
    {

        List<Account> account = accountrepo.findBypinCode(pincode);
        return account;
    }

    public List<Transcation> getTranscationByDate(LocalDate date1,LocalDate date2)
    {

        List<Transcation> transcation = transcationrepo.findBytransDateBetween(date1,date2);
        return transcation;
    }

}
