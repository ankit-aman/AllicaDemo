package com.example.allicademo2.serviceimpl;

import com.example.allicademo2.dto.CustomerDto;
import com.example.allicademo2.entity.Customer;
import com.example.allicademo2.exception.CustomerNotFoundException;
import com.example.allicademo2.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CustomerServiceUnitTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerDto customerDto;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(1)
                .firstName("Ankit")
                .lastName("Aman")
                .dob(LocalDate.of(2022, 1, 8)).build();

        customerDto = CustomerDto.builder()
                .id(1)
                .firstName("Ankit")
                .lastName("Aman")
                .dob(LocalDate.of(2022, 1, 8)).build();
    }

    @Test
    void createCustomer() {

        given(customerRepository.save(customer)).willReturn(customer);
        Integer savedEmployee = customerService.createCustomer(customerDto);
        System.out.println(savedEmployee);
        assertThat(savedEmployee).isNotNull();
    }


    @Test
    void getCustomer() throws CustomerNotFoundException {
        given(customerRepository.findById(1)).willReturn(Optional.of(customer));
        CustomerDto existingEmployee = customerService.getCustomer(customer.getId());
        System.out.println(existingEmployee);
        assertThat(existingEmployee).isNotNull();

    }


}