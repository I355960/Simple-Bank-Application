package com.finalproject.bank.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

/* Account Entity*/


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Account {

    @Id
    @Column
    private String accountNo = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);

    @Column
    private LocalDate date  =  LocalDate.now();

    @Column
    private String accountType;

    @Column
    private String customerName;

    @Column
    private String panCard;

    @Column
    private String customerAddress;

    @Column
    private String pinCode;

    @Column
    private String contactNumber;

    @Column
    private double balance;

    @ManyToOne
    @JsonBackReference(value = "branchToAccount")
    @JoinColumn(name = "ifscCode")
    private Branch branch;

    @OneToMany(cascade=ALL, mappedBy="account")
    @JsonManagedReference(value = "accountToTranscation")
    private List<Transcation> transcation;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Transcation> getTranscation() {
        return transcation;
    }

    public void setTranscation(List<Transcation> transcation) {
        this.transcation = transcation;
    }
}
