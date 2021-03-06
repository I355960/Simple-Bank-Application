package com.finalproject.bank.Api;

import com.finalproject.bank.Entity.Account;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Entity.Branch;
import com.finalproject.bank.Entity.Transcation;
import com.finalproject.bank.Exception.bankExceptionController;
import com.finalproject.bank.Service.bankService;

import com.finalproject.bank.Structure.fundTransferStructure;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BankApi {

    @Autowired
    private bankService bankservice;

    @PostMapping("/bank")
    public String createBank(@RequestBody Bank bank)
    {
        try{
            return bankservice.createBank(bank);
        }
        catch (bankExceptionController e)
        {
            return e.getErrorMessage();
        }
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

    @GetMapping("/name/{id}")
    private List<Account> getCustomerName(@PathVariable("id") String id)
    {
        return bankservice.getCustomerByName(id);
    }

    @GetMapping("/pincode/{pinCode}")
    private List<Account> getCustomerPincode(@PathVariable("pinCode") String pinCode)
    {
        return bankservice.getCustomerByPincode(pinCode);
    }

    @GetMapping("/date/{date1}/{date2}")
    private List<Transcation> getByTranscationDate(@PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1, @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2)
    {
        return bankservice.getTranscationByDate(date1,date2);
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

    @PostMapping("/transfer")
    public String fundTransfer(@RequestBody fundTransferStructure account)
    {
        return bankservice.fundTransfer(account);
    }

    @GetMapping("/id/{id}/{date1}/{date2}")
    public List<Transcation> getTranscationWithId(@PathVariable("id") String id,@PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1, @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2)
    {
        return bankservice.getAllIdWithTranscation(id,date1,date2);
    }

    @GetMapping("/accountid/{id}/{date1}/{date2}")
    public List<Transcation> getTranscationWithIdAndDate(@PathVariable("id") String id,@PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1, @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2)
    {
        List<Transcation> transdata =bankservice.getTranscationWithIdAndDate(id,date1,date2);
        if( transdata.isEmpty())
        throw new bankExceptionController("No transcation is happened in between date " +date1 +" and " +date2);
        else
        return transdata;
    }

    @PatchMapping("/updatebranch/{id}")
    private String updateBranch(@RequestBody Branch branch, @PathVariable("id") String id)
    {
        return bankservice.recordUpdateBranch(branch,id);
    }



}
