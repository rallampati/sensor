package com.egen.sensor.service;

import com.egen.sensor.domain.Metric;
import com.egen.sensor.repository.AlertRepository;
import com.egen.sensor.repository.MetricRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mongodb.morphia.Datastore;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ram on 11/29/17.
 */
@RunWith(SpringRunner.class)
public class MetricServiceImplTest {

    @Mock
    private MetricRepository metricRepository;

    @Mock
    private AlertRepository alertRepository;

    private MetricService metricService;

    @Before
    public void setUp() {

        metricService = new MetricServiceImpl(metricRepository, alertRepository);
    }

    @Test
    public void save() throws Exception {

        Metric metric = new Metric(new Date(), 155);
        metricService.save(metric);
        verify(metricRepository).save(metric);
    }

    @Test
    public void getAllMetrics() throws Exception {

        when(metricRepository.findAll()).thenReturn(Arrays.asList(new Metric(new Date(), 155)));
        List<Metric> metricList = metricService.getAllMetrics();
        assertThat(metricList).isNotEmpty();
        assertThat(metricList).hasSize(1);

    }

    @Test
    public void getMetricsByDateRange() throws Exception {

        when(metricRepository.findAllByDateRange(any(), any())).thenReturn(Arrays.asList(new Metric(new Date(),
                160), new Metric(new Date(), 175)));

        List<Metric> metricList = metricService.getMetricsByDateRange(new Date(), new Date());
        assertThat(metricList).isNotEmpty();
        assertThat(metricList).hasSize(2);

    }

}