package de.telran.repository;

import de.telran.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Transactional
    @Query("select s from Status s where s.shipmentId = :shipmentId order by s.trackingId desc")
    List<Status> getAllStatusesOfAShipment(@Param("shipmentId") long shipmentId);
}
