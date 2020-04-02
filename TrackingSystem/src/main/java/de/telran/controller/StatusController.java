package de.telran.controller;

import de.telran.dto.StatusDTO;
import de.telran.entity.Status;
import de.telran.service.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatusController {

    private StatusService statusService;

    private ModelMapper modelMapper;

    @Autowired
    public StatusController(StatusService statusService, ModelMapper modelMapper) {
        this.statusService = statusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/statuses")
    List<StatusDTO> getAllStatuses() {
        return  statusService.getAllStatuses()
                .stream()
                .map(this::convertStatusToStatusDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/statuses")
    StatusDTO createTrackingStatus (@RequestBody StatusDTO statusDTO) {
        Status status = convertStatusDTOToStatus(statusDTO);
        return convertStatusToStatusDTO(statusService.createTrackingStatus(status));
    }

    private StatusDTO convertStatusToStatusDTO(Status status) {
        return modelMapper.map(status, StatusDTO.class);
    }

    private Status convertStatusDTOToStatus(StatusDTO statusDTO) {
        return modelMapper.map(statusDTO, Status.class);
    }

}



/*@GetMapping("/api/statuses/?shipment_id={shipment_id}")
    Status getAllStatusesOfAShipment(@PathVariable("shipment_id") Long shipmentId) {
        return statusService.getAllStatusesOfAShipment(shipmentId).get(0);
    }

    @GetMapping("/api/last_statuses/{customer_id}")
    List<ShipmentsOfCustomerDTO> getTheLastStatusesOfAllShipmentsOfACustomer(@PathVariable("customer_id") Long customerId) {
        return statusService.getTheLastStatusesOfAllShipmentsOfACustomer(customerId);
    }*/
