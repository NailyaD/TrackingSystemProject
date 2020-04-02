package de.telran.controller;

import de.telran.dto.CustomerDTO;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    CustomerService customerService;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    List<CustomerDTO> getAllCustomers() {
        return  customerService.getAllCustomers()
                .stream()
                .map(this::convertCustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("api/customers/{customer_id}/shipments")
    CustomerDTO getAllShipmentsOfACustomer(@PathVariable("customer_id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return convertCustomerToCustomerDTO(customer);
    }

    @GetMapping("api/customers/{customer_id}/shipments/{shipment_id}/statuses")
    Optional<Shipment> getCurrentStatusesOfAShipmentOfACustomer(@PathVariable("customer_id") Long customerId,
                                                                @PathVariable("shipment_id") Long shipmentId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Shipment> shipments = customer.getShipments();
        return shipments.stream().filter(shipment -> shipment.getShipmentId().equals(shipmentId)).findFirst();
    }

    /*@GetMapping("api/customers/{customer_id}/shipments/{shipment_id}/statuses")
    CustomerDTO getCurrentStatusesOfAShipmentOfACustomer(@PathVariable("customer_id") Long customerId,
                                                         @PathVariable("shipment_id") Long shipmentId) {
        Customer customerShipments = customerService.getAllCustomers().stream()
                               .filter(customer -> customer.getCustomerId().equals(customerId))
                               .filter(customer -> customer.getShipments().stream()
                               .filter(shipment -> shipment.getShipmentId().equals(shipmentId)).findFirst()).findFirst();
        return convertCustomerToCustomerDTO(customerShipments);
    }*/

    @PostMapping("/api/customers")
    CustomerDTO createCustomer (@RequestBody CustomerDTO customerDto) {
        Customer customerEntity = convertCustomerDTOToCustomer(customerDto);
        return convertCustomerToCustomerDTO(customerService.createCustomer(customerEntity));
    }

    @DeleteMapping("api/customers/{customer_id}")
    public Long deleteCustomer(@PathVariable("customer_id") Long id) {
        return customerService.deleteCustomer(id);
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }


}
