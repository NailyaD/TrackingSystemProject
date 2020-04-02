package de.telran.controller;

import de.telran.entity.Shipment;
import de.telran.entity.Status;
import de.telran.service.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipmentController.class)
@Import(ModelMapper.class)
public class ShipmentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShipmentService shipmentService;

    @Test
    public void testGetAllShipments() throws Exception {
        when(shipmentService.getAllShipments()).thenReturn(createListOfShipments());

        mvc.perform(get("/api/shipments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].shipmentId").value("1"))
                .andExpect(jsonPath("$[0].shipmentTitle").value("Shipment"))
                .andExpect(jsonPath("$[0].statuses[0].trackingId").value("1"))
                .andExpect(jsonPath("$[0].statuses[0].statusTitle").value("Shipped"))
                .andExpect(jsonPath("$[0].statuses[1].trackingId").value("2"))
                .andExpect(jsonPath("$[0].statuses[1].statusTitle").value("Delivered"))

                .andExpect(jsonPath("$[1].shipmentId").value("2"))
                .andExpect(jsonPath("$[1].shipmentTitle").value("Letter"));
    }

    private List<Shipment> createListOfShipments() {

        Status status1 = new Status();
        status1.setTrackingId(1L);
        status1.setStatusTitle("Shipped");

        Status status2 = new Status();
        status2.setTrackingId(2L);
        status2.setStatusTitle("Delivered");

        List<Status> statuses = Arrays.asList(status1, status2);

        Shipment shipment1 = new Shipment();
        shipment1.setShipmentId(1L);
        shipment1.setShipmentTitle("Shipment");
        shipment1.setStatuses(statuses);

        Shipment shipment2 = new Shipment();
        shipment2.setShipmentId(2L);
        shipment2.setShipmentTitle("Letter");

        return Arrays.asList(shipment1, shipment2);
    }

    @Test
    public void testCreateNewShipment() throws Exception {
        Shipment shipment = new Shipment();
        shipment.setShipmentId(5L);
        shipment.setShipmentTitle("Letter");

        Shipment savedShipment = new Shipment();
        savedShipment.setShipmentId(5L);
        savedShipment.setShipmentTitle("Letter");
        when(shipmentService.createShipment(shipment)).thenReturn(savedShipment);

        mvc.perform(post("/api/shipments")
                .content("{\"shipmentId\": \"5\",\"shipmentTitle\":\"Letter\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(shipmentService, times(1)).createShipment(shipment);
    }

    /*@Test
    public void testGetAllShipmentsOfACustomer() throws Exception {
        Long customerId = 6L;
        when(shipmentService.getAllShipmentsOfACustomer(customerId)).thenReturn(getShipmentsByACustomer());

        mvc.perform(get("/api/shipments/?customer_id={customer_id}", 6L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].shipmentId").value("1"))
                .andExpect(jsonPath("$[0].shipmentTitle").value("Shipment"))
                .andExpect(jsonPath("$[0].customerId").value("6"))
                .andExpect(jsonPath("$[1].shipmentId").value("2"))
                .andExpect(jsonPath("$[1].shipmentTitle").value("Postal Packet"))
                .andExpect(jsonPath("$[1].customerId").value("6"));

    }


public List<Shipment> getShipmentsByACustomer() {
        Shipment shipment1 = new Shipment();
        shipment1.setShipmentId(1L);
        shipment1.setShipmentTitle("Shipment");
        shipment1.setCustomerId(6L);

        Shipment shipment2 = new Shipment();
        shipment2.setShipmentId(2L);
        shipment2.setShipmentTitle("Postal Packet");
        shipment2.setCustomerId(6L);

        Shipment shipment3 = new Shipment();
        shipment3.setShipmentId(3L);
        shipment3.setShipmentTitle("Letter");
        shipment3.setCustomerId(5L);

        return Arrays.asList(shipment1, shipment2, shipment3);
    }*/


}
