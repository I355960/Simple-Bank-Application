package com.finalproject.bank.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Account {

    @Id
    @Column
    private String accountNo = UUID.randomUUID().toString();

    @Column
    private LocalDate date =  LocalDate.now();

    @Column
    private String customerName;

    @Column
    private String panCard;

    @Column
    private String customerAddress;

    @Column
    private String pinCode;

    @Column
    private double balance;

    @ManyToOne
    @JsonBackReference(value = "branchToAccount")
    @JoinColumn(name = "ifscCode")
    private Branch branch;
}
