package de.telran.service;

import de.telran.dto.ShipmentsOfCustomerDTO;
import de.telran.entity.Status;
import de.telran.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<ShipmentsOfCustomerDTO> getTheLastStatusesOfAllShipmentsOfACustomer(Long customerId) {
        return statusRepository.getStatusesOfAllShipmentsOfACustomer(customerId).stream()
                .filter(distinctByKey(ShipmentsOfCustomerDTO::getShipmentId))
                .collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
