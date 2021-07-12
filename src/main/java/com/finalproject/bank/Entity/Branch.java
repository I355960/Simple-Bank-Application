package com.finalproject.bank.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


/* Branch */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    @Column
    private String ifscCode;

    @Column
    private String branchName;

    @Column
    private String address;

    @Column
    private Integer pinCode;

    @ManyToOne
    @JsonBackReference(value = "bankToBranch")
    @JoinColumn(name = "bankCode")
    private Bank bank;

    @OneToMany(cascade=ALL, mappedBy="branch")
    @JsonManagedReference(value = "branchToAccount")
    private List<Account> account;

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}
