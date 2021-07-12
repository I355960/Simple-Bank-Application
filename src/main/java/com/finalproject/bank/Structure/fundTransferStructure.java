package com.finalproject.bank.Structure;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration

public class fundTransferStructure {

    private String senderBankID;
    private String senderBankIfsc;
    private String senderAccountId;


    private String receiverBankID;
    private String receiverBankIfsc;
    private String receiverAccountId;

    private double amount;
    private String remarks;

    public String getSenderBankID() {
        return senderBankID;
    }

    public void setSenderBankID(String senderBankID) {
        this.senderBankID = senderBankID;
    }

    public String getSenderBankIfsc() {
        return senderBankIfsc;
    }

    public void setSenderBankIfsc(String senderBankIfsc) {
        this.senderBankIfsc = senderBankIfsc;
    }

    public String getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(String senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public String getReceiverBankID() {
        return receiverBankID;
    }

    public void setReceiverBankID(String receiverBankID) {
        this.receiverBankID = receiverBankID;
    }

    public String getReceiverBankIfsc() {
        return receiverBankIfsc;
    }

    public void setReceiverBankIfsc(String receiverBankIfsc) {
        this.receiverBankIfsc = receiverBankIfsc;
    }

    public String getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(String receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
