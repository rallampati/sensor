package com.egen.sensor.controller;

import com.egen.sensor.domain.Alert;
import com.egen.sensor.domain.Metric;
import com.egen.sensor.service.AlertService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ram on 11/29/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AlertController.class)
public class AlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlertService alertService;

    @Test
    public void getAllAlerts() throws Exception {

        List<Alert> alertList = Arrays.asList(new Alert(new Date(), "Over weight alert"), new Alert(new Date(), "Under weight alert"));
        BDDMockito.given(alertService.getAllAlerts()).willReturn(alertList);

        mockMvc.perform(get("/alert/getAllAlerts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getAlertsByDateRange() throws Exception {

        List<Alert> alertList = Arrays.asList(new Alert(new Date(), "Over weight alert"), new Alert(new Date(), "Under weight alert"));
        BDDMockito.given(alertService.getAlertsByDateRange(any(), any())).willReturn(alertList);

        mockMvc.perform(get("/alert/getAlertsByDateRange?from=11-28-2017 21:11:34&to=11-28-2017 21:13:32")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].alertMessage", is("Over weight alert")));

    }

}