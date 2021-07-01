package com.finalproject.bank.Api;

import com.finalproject.bank.Entity.Account;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Entity.Transcation;
import com.finalproject.bank.Service.bankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankApi {

    @Autowired
    private bankService bankservice;

    @PostMapping("/bank")
    public Bank createBank(@RequestBody Bank bank)
    {
        return bankservice.createBank(bank);
    }

    @GetMapping("/bank")
    private List<Bank> getAllBank()
    {
        return bankservice.getAllBank();
    }

    @GetMapping("/search/{id}")
    private Account getCustomerId(@PathVariable("id") String id)
    {
        return bankservice.getCustomerById(id);
    }

    @GetMapping("/search/name/{id}")
    private Account getCustomerName(@PathVariable("id") String id)
    {
        return bankservice.getCustomerById(id);
    }


    @DeleteMapping("/del/{id}")
    private String deleteBank(@PathVariable("id") String id)
    {
        return bankservice.deleteCustomer(id);
    }

    @PatchMapping("/update/{id}")
    private String updateBank(@RequestBody Account account,@PathVariable("id") String id)
    {
        return bankservice.recordUpdate(account,id);
    }

    @PostMapping("/credit/{id}")
    public String creditBal(@RequestBody Transcation trans,@PathVariable("id") String id)
    {
        return bankservice.getCreditAmount(trans,id);
    }

    @PostMapping("/debit/{id}")
    public String debitBal(@RequestBody Transcation trans,@PathVariable("id") String id)
    {
        return bankservice.getDebitAmount(trans,id);
    }



}
