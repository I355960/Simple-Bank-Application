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

}
