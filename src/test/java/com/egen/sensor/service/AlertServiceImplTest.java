package com.egen.sensor.service;

import com.egen.sensor.domain.Alert;
import com.egen.sensor.repository.AlertRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ram on 11/29/17.
 */
@RunWith(SpringRunner.class)
public class AlertServiceImplTest {

    @Mock
    private AlertRepository alertRepository;

    private AlertService alertService;

    @Before
    public void setUp() {
        alertService = new AlertServiceImpl(alertRepository);
    }

    @Test
    public void getAllAlerts() throws Exception {

        when(alertRepository.findAll()).thenReturn(Arrays.asList(new Alert(new Date(), "Under weight alert")));
        List<Alert> alertList = alertService.getAllAlerts();
        assertThat(alertList).isNotEmpty();
        assertThat(alertList).hasSize(1);
    }

    @Test
    public void getAlertsByDateRange() throws Exception {

        when(alertRepository.findAllByDateRange(any(), any())).thenReturn(Arrays.asList(new Alert(new Date(),
                "Under weight alert"), new Alert(new Date(), "Over weight alert")));

        List<Alert> alertList = alertService.getAlertsByDateRange(new Date(), new Date());
        assertThat(alertList).isNotEmpty();
        assertThat(alertList).hasSize(2);
    }

}