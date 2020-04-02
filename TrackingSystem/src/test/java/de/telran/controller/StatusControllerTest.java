package de.telran.controller;

import de.telran.entity.Status;
import de.telran.service.StatusService;
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
@WebMvcTest(StatusController.class)
@Import(ModelMapper.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatusService statusService;

    @Test
    public void testGetAllStatuses() throws Exception {
        when(statusService.getAllStatuses()).thenReturn(createListOfStatuses());

        mvc.perform(get("/api/statuses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].trackingId").value("1"))
                .andExpect(jsonPath("$[0].statusTitle").value("Shipped"))

                .andExpect(jsonPath("$[1].trackingId").value("2"))
                .andExpect(jsonPath("$[1].statusTitle").value("Delivered"));
    }

    private List<Status> createListOfStatuses() {

        Status status1 = new Status();
        status1.setTrackingId(1L);
        status1.setStatusTitle("Shipped");

        Status status2 = new Status();
        status2.setTrackingId(2L);
        status2.setStatusTitle("Delivered");

        return Arrays.asList(status1, status2);
    }

    @Test
    public void testCreateNewStatus() throws Exception {
        Status status = new Status();
        status.setTrackingId(1L);
        status.setStatusTitle("Shipped");

        Status savedStatus = new Status();
        savedStatus.setTrackingId(1L);
        savedStatus.setStatusTitle("Shipped");

        when(statusService.createTrackingStatus(status)).thenReturn(savedStatus);

        mvc.perform(post("/api/statuses")
                .content("{\"trackingId\": \"1\",\"statusTitle\":\"Shipped\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(statusService, times(1)).createTrackingStatus(status);
    }
}
