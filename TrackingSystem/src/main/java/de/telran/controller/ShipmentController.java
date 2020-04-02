package de.telran.controller;

import de.telran.dto.ShipmentDTO;
import de.telran.entity.Shipment;
import de.telran.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShipmentController {

    private ShipmentService shipmentService;

    private ModelMapper modelMapper;

    @Autowired
    public ShipmentController(ShipmentService shipmentService, ModelMapper modelMapper) {
        this.shipmentService = shipmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/shipments")
    List<ShipmentDTO> getAllShipments() {
        return  shipmentService.getAllShipments()
                .stream()
                .map(this::convertShipmentToShipmentDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/shipments")
    ShipmentDTO createShipment (@RequestBody ShipmentDTO shipmentDTO) {
        Shipment shipmentEntity = convertShipmentDTOToShipment(shipmentDTO);
        return convertShipmentToShipmentDTO(shipmentService.createShipment(shipmentEntity));
    }

    private ShipmentDTO convertShipmentToShipmentDTO(Shipment shipment) {
        return modelMapper.map(shipment, ShipmentDTO.class);
    }

    private Shipment convertShipmentDTOToShipment(ShipmentDTO shipmentDTO) {
        return modelMapper.map(shipmentDTO, Shipment.class);
    }
}
