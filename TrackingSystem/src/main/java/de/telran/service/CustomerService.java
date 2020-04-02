package de.telran.service;

import de.telran.entity.Customer;
import de.telran.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Long deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return id;
    }
}


/*public Customer getCustomerById (Long customerId) {
        return customerRepository.getOne(customerId);
    }
*/
