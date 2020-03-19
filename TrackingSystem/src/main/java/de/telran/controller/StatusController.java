package de.telran.controller;

import de.telran.dto.ShipmentsOfCustomerDTO;
import de.telran.entity.Status;
import de.telran.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusController {

    StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/api/statuses")
    List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @PostMapping("/api/statuses")
    Status createTrackingStatus (@RequestBody Status status) {
        return statusService.createTrackingStatus(status);
    }

    @GetMapping("/api/statuses/{shipment_id}")
    Status getAllStatusesOfAShipment(@PathVariable("shipment_id") Long shipmentId) {
        return statusService.getAllStatusesOfAShipment(shipmentId).get(0);
    }

    @GetMapping("/api/last_statuses/{customer_id}")
    List<ShipmentsOfCustomerDTO> getTheLastStatusesOfAllShipmentsOfACustomer(@PathVariable("customer_id") Long customerId) {
        return statusService.getTheLastStatusesOfAllShipmentsOfACustomer(customerId);
    }

}
