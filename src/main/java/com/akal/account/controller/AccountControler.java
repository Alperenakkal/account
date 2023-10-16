package com.akal.account.controller;

import com.akal.account.dto.AccountDto;
import com.akal.account.dto.CreateAccountRequest;
import com.akal.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/account")
public class AccountControler {

    private final AccountService accountService;

    public AccountControler(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
