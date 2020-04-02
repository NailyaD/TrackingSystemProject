package de.telran.controller;

import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.entity.Status;
import de.telran.service.CustomerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@Import(ModelMapper.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

@Test
    public void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(createListOfCustomers());

        mvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].customerId").value("1"))
                .andExpect(jsonPath("$[0].customerName").value("Ivan Ivanov"))
                .andExpect(jsonPath("$[0].shipments[0].shipmentId").value("1"))
                .andExpect(jsonPath("$[0].shipments[0].shipmentTitle").value("Shipment"))
                .andExpect(jsonPath("$[0].shipments[0].statuses[0].trackingId").value("3"))
                .andExpect(jsonPath("$[0].shipments[0].statuses[0].statusTitle").value("Cancelled"))
                .andExpect(jsonPath("$[0].shipments[1].shipmentId").value("2"))
                .andExpect(jsonPath("$[0].shipments[1].shipmentTitle").value("Postal Packet"))

                .andExpect(jsonPath("$[1].customerId").value("2"))
                .andExpect(jsonPath("$[1].customerName").value("Piotr Petrov"));
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        Customer customer = new Customer(1L, "Ivan Ivanov", null);
        Customer savedCustomer = new Customer(1L, "Ivan Ivanov", null);
        when(customerService.createCustomer(customer)).thenReturn(savedCustomer);

        mvc.perform(post("/api/customers")
                .content("{\"customerId\": \"1\",\"customerName\":\"Ivan Ivanov\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(customerService, times(1)).createCustomer(customer);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Long customerId = 6L;
        when(customerService.deleteCustomer(customerId)).thenReturn(customerId);
        mvc.perform(delete("/api/customers/" + customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(customerService, times(1)).deleteCustomer(customerId);
    }

    private List<Customer> createListOfCustomers() {
        Status status1 = new Status();
        status1.setTrackingId(3L);
        status1.setStatusTitle("Cancelled");

        Shipment shipment1 = new Shipment();
        shipment1.setShipmentId(1L);
        shipment1.setShipmentTitle("Shipment");
        shipment1.setStatuses(Arrays.asList(status1));

        Shipment shipment2 = new Shipment();
        shipment2.setShipmentId(2L);
        shipment2.setShipmentTitle("Postal Packet");

        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        customer1.setCustomerName("Ivan Ivanov");
        customer1.setShipments(Arrays.asList(shipment1, shipment2));

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customer2.setCustomerName("Piotr Petrov");

        return Arrays.asList(customer1, customer2);
    }
}
