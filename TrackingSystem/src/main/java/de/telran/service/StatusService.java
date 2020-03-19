package de.telran.service;

import de.telran.entity.Status;
import de.telran.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status createTrackingStatus(Status status) {
        return statusRepository.save(status);
    }

    public List<Status> getAllStatusesOfAShipment(Long shipmentId) {
        return statusRepository.getAllStatusesOfAShipment(shipmentId);
    }


}
