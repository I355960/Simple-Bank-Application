package com.finalproject.bank.Exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class bankExceptionController extends RuntimeException {

    private  String errorMessage;
    private String errorCode;
    private static final long serialVersionUID = 1L;

}
