package de.telran.controller;

import de.telran.entity.Shipment;
import de.telran.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipmentController {

    ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/api/shipments")
    List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @PostMapping("/api/shipments")
    Shipment createShipment (@RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment);
    }

    @GetMapping("/api/shipments/?customer_id={customer_id}")
    List<Shipment> getAllShipmentsOfACustomer(@PathVariable("customer_id") Long customerId) {
        return shipmentService.getAllShipmentsOfACustomer(customerId);
    }
}
