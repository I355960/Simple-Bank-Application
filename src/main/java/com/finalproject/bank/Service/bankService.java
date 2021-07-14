package com.finalproject.bank.Service;

import com.finalproject.bank.Entity.Account;
import com.finalproject.bank.Entity.Bank;
import com.finalproject.bank.Entity.Branch;
import com.finalproject.bank.Entity.Transcation;
import com.finalproject.bank.Exception.bankExceptionController;
import com.finalproject.bank.Repositatory.accountRepo;
import com.finalproject.bank.Repositatory.bankRepo;
import com.finalproject.bank.Repositatory.branchRepo;
import com.finalproject.bank.Repositatory.transcationRepo;

import com.finalproject.bank.Structure.fundTransferStructure;
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
    private Transcation transcation;
    @Autowired
    private Bank bank;
    @Autowired
    private transcationRepo transcationrepo;
    @Autowired
    private branchRepo branchrepo;

/*Create a new account*/

    public String createBank( Bank bank)
    {

            Branch branch1 = bank.getBranch().get(0);
            Account account1 = branch1.getAccount().get(0);
            if (account1.getTranscation().get(0).getCreditBalance() < 1000 ) {
                throw new bankExceptionController("Your Account is not created. Due to minimum balance not is maintained");
            }
            try{
                bank.setBankCode(bank.getBankCode());
                bank.setBankName(bank.getBankName());
                bank.setAddress(bank.getAddress());
                bank.setPinCode(bank.getPinCode());
                account1.setBalance(account1.getTranscation().get(0).getCreditBalance());
                bankrepo.save(bank);
                return "Congratulations!!New Account is created. New Account number is : " + account1.getAccountNo();

        }catch (Exception e) {
            throw new bankExceptionController("Invaild entry.");
        }

    }

/* View Accounts */
    public List<Bank> getAllBank()
    {
        List<Bank> bank = new ArrayList<Bank>();
        bankrepo.findAll().forEach(bank1 -> bank.add(bank1));
        return bank;

    }
/* Delete Account*/
    public String deleteCustomer(String accountNo)
    {
       accountrepo.deleteById(accountNo);
        return "Delete customer record : " +accountNo;
    }
/* Update account */

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
            return "Customer id " +id +" record is updated";
        }
        return "Record not found";

    }
/* Search account by id */
    public Account getCustomerById(String id)
    {

        account = accountrepo.findById(id).get();
        return account;
    }

    /* Account credit */

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

        return +transc.getCreditBalance() +" is credited in Account number: " +id +" . Total balance is " +account.getBalance();

    }

    /* Account debit */

    public String getDebitAmount(Transcation transc,String id)
    {
        account = accountrepo.findById(id).get();
        if(account.getBalance()>= transc.getDebitBalance()) {
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

    /* Search account by name */
    public List<Account> getCustomerByName(String name)
    {

        List<Account> account = accountrepo.findBycustomerNameContaining(name);
        return account;
    }
    /* Search account by pincode*/

    public List<Account> getCustomerByPincode(String pincode)
    {

        List<Account> account = accountrepo.findBypinCode(pincode);
        return account;
    }

    /* Transcation by filter in date */
    public List<Transcation> getTranscationByDate(LocalDate date1,LocalDate date2)
    {

        List<Transcation> transcation = transcationrepo.findBytransDateBetween(date1,date2);
        return transcation;
    }

    /* Fund transfer */

    public String fundTransfer(fundTransferStructure transferAmount)
    {

        bankrepo.findById(transferAmount.getSenderBankID()).get();
        branchrepo.findById(transferAmount.getSenderBankIfsc()).get();
        account = accountrepo.findById(transferAmount.getSenderAccountId()).get();

        bankrepo.findById(transferAmount.getReceiverBankID()).get();
        branchrepo.findById(transferAmount.getReceiverBankIfsc()).get();
        accountrepo.findById(transferAmount.getReceiverAccountId()).get();

        Transcation transc1 = new Transcation();
        transc1.setDebitBalance(transferAmount.getAmount());
        transc1.setCreditBalance(transferAmount.getAmount());

        if (account.getBalance()>=transferAmount.getAmount()) {
            String remarks = "Transfer to ACCOUNT NO. " +transferAmount.getReceiverAccountId();
            transc1.setRemarks(remarks);
            getDebitAmount(transc1, transferAmount.getSenderAccountId());
            remarks = "Received from ACCOUNT NO. " +transferAmount.getSenderAccountId();
            transc1.setRemarks(remarks);
            getCreditAmount(transc1, transferAmount.getReceiverAccountId());
            return "Transfer amount: " +transferAmount.getAmount();
        }
        else
            return "Unavailable to transfer amount.";
    }

    /*  Transcation History of a particular id */

    public List<Transcation> getAllIdWithTranscation( String accountId,LocalDate dateStart,LocalDate dateEnd)
    {
        int i;
        List<Transcation> transcation = new ArrayList<Transcation>();
        List<Transcation> transcationFilter = new ArrayList<Transcation>();

        account = accountrepo.findById(accountId).get();
        int size = account.getTranscation().size();

        for(i=0;i<size;i++) {

            transcation.add(account.getTranscation().get(i));
        }

        for(i=0;i<size;i++)
        {
            if(transcation.get(i).getTransDate().isAfter(dateStart) && transcation.get(i).getTransDate().isBefore(dateEnd))
            {
                transcationFilter.add(transcation.get(i));

            }
        }
        return transcationFilter;

    }
    public List<Transcation> getTranscationWithIdAndDate(String accountId,LocalDate dateStart,LocalDate dateEnd)
    {
        List<Transcation> transcation1 = new ArrayList<Transcation>();

        transcation1 = transcationrepo.findAllAccountIdBetweenDate(accountId,dateStart,dateEnd);
        return transcation1;
    }

    public String recordUpdateBranch(Branch branch, String id)
    {
        Branch branchUpdate = branchrepo.findById(id).get();
        if(branchUpdate.getIfscCode() == id)
        {
            branchUpdate.setBranchName(branch.getBranchName());
            branchUpdate.setAddress(branch.getAddress());
            branchUpdate.setPinCode(branch.getPinCode());
            //branchUpdate.setIfscCode(branch.getIfscCode());
            branchrepo.save(branchUpdate);
            return "Branch id " +id +" records are updated";
        }
        return "Record not found";

    }
}
