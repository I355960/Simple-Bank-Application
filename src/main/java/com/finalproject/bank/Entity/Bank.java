package com.finalproject.bank.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/* Bank Entity*/


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Bank {

    @Id
    @Column

    private String bankCode;

    @Column
    private String bankName;

    @Column
    private String address;

    @Column
    private Integer pinCode;

    @OneToMany(cascade=ALL, mappedBy="bank")
    @JsonManagedReference(value = "bankToBranch")
    private List<Branch> branch;


}
