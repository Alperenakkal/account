package com.akal.account.service;

import com.akal.account.dto.CustomerDto;
import com.akal.account.dto.CustomerDtoConverter;
import com.akal.account.exception.CustomerNotFoundException;
import com.akal.account.model.Customer;
import com.akal.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }
    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(
                () ->new CustomerNotFoundException("Customer could not find by id:"+ id));

    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }
}
