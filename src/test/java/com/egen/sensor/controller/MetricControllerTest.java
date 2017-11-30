package com.egen.sensor.controller;

import com.egen.sensor.domain.Metric;
import com.egen.sensor.service.MetricService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.BDDAssertions;
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
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ram on 11/29/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MetricController.class)
public class MetricControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MetricService metricService;

    @Test
    public void createMetric() throws Exception {

        Metric metric = new Metric(new Date(), 150);

        mockMvc.perform(put("/metric/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(metric)))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllMetrics() throws Exception {

        List<Metric> metricList = Arrays.asList(new Metric(new Date(), 150), new Metric(new Date(), 165));
        BDDMockito.given(metricService.getAllMetrics()).willReturn(metricList);

        mockMvc.perform(get("/metric/getAllMetrics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].value", is(150)));
    }

    @Test
    public void getMetricsByDateRange() throws Exception {

        List<Metric> metricList = Arrays.asList(new Metric(new Date(), 150), new Metric(new Date(), 165));
        BDDMockito.given(metricService.getMetricsByDateRange(any(), any())).willReturn(metricList);

        mockMvc.perform(get("/metric/getMetricsByDateRange?from=11-28-2017 21:11:34&to=11-28-2017 21:13:32")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].value", is(150)));
    }

}