package com.finalproject.bank.TestApi;

import com.finalproject.bank.Api.BankApi;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Entity.Branch;
import com.finalproject.bank.Repositatory.bankRepo;
import com.finalproject.bank.Service.bankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BankApiTest {

    @Autowired
    private bankService bankservice;
    @MockBean
    private bankRepo bankrepo;

    @Test
    public void createBankNullCheckTest()
    {
        Bank bank = new Bank();
        assertEquals(bank.getBankName(),null);

    }

    @Test
    public void createBankCheckTest()
    {
        Bank bank = new Bank();
        bank.setBankName("SBI");
        bank.setAddress("blr");
        bank.setPinCode(1234);

        bank.setBranch(null);

        when(bankrepo.save(bank)).thenReturn(bank);


        assertEquals(bank.getBankName(),"SBI");
        assertEquals(bank.getAddress(),"blr");
        assertEquals(bank.getPinCode(),1234);
        assertEquals(bank.getBranch(),null);

    }

    @Test
    public void getBankAndUser()
    {
        when(bankrepo.findAll()).thenReturn(Stream.of(new Bank("12","sbi","blr",1234,null)).collect(Collectors.toList()));
        assertEquals(1,bankservice.getAllBank().size());
    }

}
