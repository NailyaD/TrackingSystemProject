package de.telran.controller;

import de.telran.entity.Customer;
import de.telran.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/api/customers")
    Customer createCustomer (@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

}
