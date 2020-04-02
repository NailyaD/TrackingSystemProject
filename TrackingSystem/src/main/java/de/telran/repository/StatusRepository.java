package de.telran.repository;

import de.telran.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

}

 /*@Transactional
    @Query("select s from Status s where s.shipmentId = :shipmentId order by s.trackingId desc")
    List<Status> getAllStatusesOfAShipment(@Param("shipmentId") long shipmentId);

    @Query("select new de.telran.dto.ShipmentsOfCustomerDTO (s.customerId, t.trackingId, t.statusTitle, s.shipmentId, s.shipmentTitle) " +
            "from Status as t join Shipment as s on t.shipmentId = s.shipmentId where s.customerId = :customerId " +
            "order by t.trackingId desc")
    List<ShipmentsOfCustomerDTO> getStatusesOfAllShipmentsOfACustomer(@Param("customerId") long customerId);*/
