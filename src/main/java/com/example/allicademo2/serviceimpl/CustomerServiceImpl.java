package com.example.allicademo2.serviceimpl;

import com.example.allicademo2.dto.CustomerDto;
import com.example.allicademo2.entity.Customer;
import com.example.allicademo2.exception.CustomerNotFoundException;
import com.example.allicademo2.repository.CustomerRepository;
import com.example.allicademo2.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Integer createCustomer(CustomerDto customerDto) {
        Customer createdCust = new Customer();
        Integer id = 0;
        try {
            if (customerDto != null) {
                Customer customer = new Customer();
                customer.setFirstName(customerDto.getFirstName());
                customer.setLastName(customerDto.getLastName());
                customer.setDob(customerDto.getDob());
                createdCust = customerRepository.save(customer);
                id = createdCust.getId();
            }
        } catch (Exception e) {
            logger.debug("Error occurred",e);
        }
        return id;
    }

    @Override
    public CustomerDto getCustomer(Integer id) throws CustomerNotFoundException {
        CustomerDto customerDto = new CustomerDto();
        Optional<Customer> cust = Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));

        if (cust.isPresent()) {
            customerDto.setId(cust.get().getId());
            customerDto.setFirstName(cust.get().getFirstName());
            customerDto.setLastName(cust.get().getLastName());
            customerDto.setDob(cust.get().getDob());
        }
        return customerDto;
    }
}
