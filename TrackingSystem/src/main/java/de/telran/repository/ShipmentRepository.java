package de.telran.repository;

import de.telran.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> getAllShipmentsByCustomerId(@Param("customerId") long customerId);
}
