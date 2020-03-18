package de.telran.service;

import de.telran.entity.CustomerEntity;
import de.telran.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity getCustomerById (Long customerId) {
        return customerRepository.getOne(customerId);
    }

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        CustomerEntity savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }
}
