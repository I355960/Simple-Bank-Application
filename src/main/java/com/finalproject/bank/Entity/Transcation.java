package com.finalproject.bank.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.context.annotation.Configuration;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/* Transcation */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@Entity
public class Transcation {
    @Column
    @Id
    private String transId = UUID.randomUUID().toString();

    @Column
    private LocalDate transDate = LocalDate.now();

    @Column
    private LocalTime transTime =LocalTime.now();

    @Column
    private double creditBalance;

    @Column
    private double debitBalance;


    @Column
    private String remarks;

    @ManyToOne
    @JsonBackReference(value = "accountToTranscation")
    @JoinColumn(name = "accountNo")
    private Account account;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public LocalTime getTransTime() {
        return transTime;
    }

    public void setTransTime(LocalTime transTime) {
        this.transTime = transTime;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public double getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(double debitBalance) {
        this.debitBalance = debitBalance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
