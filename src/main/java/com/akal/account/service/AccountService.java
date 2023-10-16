package com.akal.account.service;

import com.akal.account.dto.AccountDto;
import com.akal.account.dto.AccountDtoConverter;
import com.akal.account.dto.CreateAccountRequest;
import com.akal.account.model.Account;
import com.akal.account.model.Customer;
import com.akal.account.model.Transaction;
import com.akal.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter){
        this.accountRepository=accountRepository;

        this.customerService = customerService;

        this.converter=converter;

    }
    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer=customerService
                .findCustomerById(createAccountRequest.getCustomerId());
        Account account= new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());

        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)>0){

            //Transaction transaction= transactionService.initiateMoney(account,createAccountRequest.getInitialCredit());
            Transaction transaction =new Transaction(createAccountRequest.getInitialCredit(),account);
            account.getTransaction().add(transaction);
        }
        return converter.convert(accountRepository.save(account));
    }


}


