package de.telran.service;

import de.telran.entity.Shipment;
import de.telran.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

}

/*public Shipment getShipmentById (Long shipmentId) {
        return shipmentRepository.getOne(shipmentId);
    }*/

    /*public List<Shipment> getAllShipmentsOfACustomer(Long customerId) {
        return shipmentRepository.getAllShipmentsByCustomerId(customerId);
    }*/
