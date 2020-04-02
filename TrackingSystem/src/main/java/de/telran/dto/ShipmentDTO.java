package de.telran.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO {
    private Long shipmentId;
    private String shipmentTitle;
    //private Long customerId;
    private List<StatusDTO> statuses;
}
