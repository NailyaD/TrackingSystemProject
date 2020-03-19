package de.telran.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentsOfCustomerDTO {
        Long customerId;
        Long tracking_id;
        String statusTitle;
        Long shipmentId;
        String shipmentTitle;
}
