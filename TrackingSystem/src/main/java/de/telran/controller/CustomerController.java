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

    // /api/customers/{customer_id}/shipments?

    //get all shipments by customer
    // GET: /api/customers/{customer_id}/shipments

    //get customer by customer_id
    // GET: /api/customers/{customer_id}

    //get tracking history of a shipment by customer
    //GET: /api/customer/{customer_id}/shipments/{shipment_id}/statuses

    //get list of shipments with current statuses for a customer
    // Customer Name | Shipment Tile | Actual Status
    // Ivan Petrov   | Packet        | Shipped
    // Petr Ivanov   | Letter        | Delivered
    //........
}
