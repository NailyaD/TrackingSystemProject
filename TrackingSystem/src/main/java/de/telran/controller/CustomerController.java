package de.telran.controller;

import de.telran.dto.CustomerDTO;
import de.telran.entity.Customer;
import de.telran.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private CustomerService customerService;

    private ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/customers")
    List<CustomerDTO> getAllCustomers() {
        return  customerService.getAllCustomers()
                .stream()
                .map(this::convertCustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

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



 /*@GetMapping("api/customers/{customer_id}/shipments")
    List<ShipmentDTO> getAllShipmentsOfACustomer(@PathVariable("customer_id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return convertCustomerToCustomerDTO(customer);
    }*/

    /*@GetMapping("api/customers/{customer_id}/shipments/{shipment_id}/statuses")
    Optional<Shipment> getCurrentStatusesOfAShipmentOfACustomer(@PathVariable("customer_id") Long customerId,
                                                                @PathVariable("shipment_id") Long shipmentId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Shipment> shipments = customer.getShipments();
        return shipments.stream().filter(shipment -> shipment.getShipmentId().equals(shipmentId)).findFirst();
    }*/


