package com.finalproject.bank.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
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
    private double totalAmount;

    @Column
    private String remarks;

    @ManyToOne
    @JsonBackReference(value = "accountToTranscation")
    @JoinColumn(name = "accountNo")
    private Account account;
}
