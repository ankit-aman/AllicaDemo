package com.example.allicademo2.service;

import com.example.allicademo2.dto.CustomerDto;
import com.example.allicademo2.exception.CustomerNotFoundException;


public interface CustomerService {

    public Integer createCustomer(CustomerDto customerDto);

    public CustomerDto getCustomer(Integer id) throws CustomerNotFoundException;

}
