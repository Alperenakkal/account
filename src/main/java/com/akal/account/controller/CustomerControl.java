package com.akal.account.controller;

import com.akal.account.dto.CustomerDto;
import com.akal.account.model.Customer;
import com.akal.account.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/customer")
public class CustomerControl {
    private final CustomerService customerService;

    public CustomerControl(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId){
        return ResponseEntity.ok(customerService.getCustomerById(customerId));

    }
}
