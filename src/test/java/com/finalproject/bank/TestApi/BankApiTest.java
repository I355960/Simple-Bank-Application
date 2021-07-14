package com.finalproject.bank.TestApi;

import com.finalproject.bank.Api.BankApi;
import com.finalproject.bank.Entity.Account;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Entity.Branch;
import com.finalproject.bank.Entity.Transcation;
import com.finalproject.bank.Repositatory.accountRepo;
import com.finalproject.bank.Repositatory.bankRepo;
import com.finalproject.bank.Service.bankService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BankApiTest {

    @Autowired
    private bankService bankservice;
    @MockBean
    private bankRepo bankrepo;
    @MockBean
    private accountRepo accountrepo;

    @Test
    public void createBankNullCheckTest()
    {
        Bank bank = new Bank();
        assertEquals(bank.getBankName(),null);

    }

    @Test
    public void createBankCheckTest()
    {


       List<Branch> branch = new ArrayList<Branch>();
       List<Account> account = new ArrayList<Account>();
       List<Transcation> transcation = new ArrayList<Transcation>();

       Transcation transcationData = new Transcation();

       transcation.add(transcationData);

        Account accountData = new Account();

        accountData.setAccountType("Saving");
        accountData.setCustomerName("Test name");
        accountData.setBalance(1000);
        accountData.setPanCard("PAN001");
        accountData.setTranscation(transcation);
        account.add(accountData);

        Branch branchData = new Branch();

        branchData.setIfscCode("BANK IFSC 01");
        branchData.setBranchName("SBI MUMBAI");
        branchData.setAddress("Mumbai");
        branchData.setPinCode(1234);
        branchData.setAccount(account);

        Bank bank = new Bank("SBI","STATE BANK OF INDIA","MUMBAI",1234,branch);


        when(bankrepo.save(bank)).thenReturn(bank);


        assertEquals(bank.getBankName(),"STATE BANK OF INDIA");
        assertEquals(bank.getAddress(),"MUMBAI");
        assertEquals(bank.getPinCode(),1234);
        assertNotNull(bank.getBranch());

    }

    @Test
    public void getBankDetails()
    {


        List<Branch> branch = new ArrayList<Branch>();
        List<Account> account = new ArrayList<Account>();
        List<Transcation> transcation = new ArrayList<Transcation>();

        Transcation transcationData = new Transcation();

        transcation.add(transcationData);

        Account accountData = new Account();

        accountData.setAccountType("Saving");
        accountData.setCustomerName("Test name");
        accountData.setBalance(1000);
        accountData.setPanCard("PAN001");
        accountData.setTranscation(transcation);
        account.add(accountData);

        Branch branchData = new Branch();

        branchData.setIfscCode("BANK IFSC 01");
        branchData.setBranchName("SBI MUMBAI");
        branchData.setAddress("Mumbai");
        branchData.setPinCode(1234);
        branchData.setAccount(account);


        when(bankrepo.findAll()).thenReturn(Stream.of(new Bank("SBI","State Bank of India","Mumbai",1234,branch)).collect(Collectors.toList()));
        assertEquals(1,bankservice.getAllBank().size());
    }


}
