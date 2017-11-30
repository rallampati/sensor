package com.egen.sensor.service;

import com.egen.sensor.domain.Metric;

import java.util.Date;
import java.util.List;

/**
 * Created by ram on 11/28/17.
 */
public interface MetricService {

    void save(Metric metric);

    List<Metric> getAllMetrics();

    List<Metric> getMetricsByDateRange(Date from, Date to);
}
