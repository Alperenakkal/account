package com.akal.account.service;

import com.akal.account.dto.CustomerDto;
import com.akal.account.dto.CustomerDtoConverter;
import com.akal.account.exception.CustomerNotFoundException;
import com.akal.account.model.Customer;
import com.akal.account.repository.CustomerRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;
    @BeforeEach
    public void setUp(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        converter= Mockito.mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository,converter);
    }
    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer =new Customer("id","name","surname", Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Customer result= service.findCustomerById("id");
        assertEquals(result,customer);
    }
    @Test
    public void testFindByCustomerId_whenCustomerIdDoesntNotExists_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,()->service.findCustomerById("id"));

    }
    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer =new Customer("id","name","surname", Set.of());
        CustomerDto customerDto =new CustomerDto("id","name","surname",Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);
        CustomerDto result= service.getCustomerById("id");
        assertEquals(result,customerDto);
    }
    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,
                ()->service.getCustomerById("id"));
        Mockito.verifyNoInteractions(converter);
    }

}