package com.egen.sensor.controller;

import com.egen.sensor.domain.Metric;
import com.egen.sensor.service.MetricService;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ram on 11/28/17.
 */
@RestController
@RequestMapping(value = "metric", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetricController {

    private MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    /**
     * @param metric
     */
    @PutMapping("create")
    public void create(@RequestBody Metric metric) {

        metricService.save(metric);

    }

    /**
     * @return
     */
    @GetMapping("getAllMetrics")
    public List<Metric> getAllMetrics() {

        return metricService.getAllMetrics();

    }

    /**
     * Finds the metrics with in a date range
     * <p>
     * e.g http://localhost:8080/metric/getByDateRange?from=11-27-2017 03:11:34&to=11-30-2017 03:11:37
     *
     * @param from
     * @param to
     * @return
     */
    @GetMapping("getMetricsByDateRange")
    public List<Metric> getMetricsByDateRange(
            @RequestParam("from") @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss") Date to) {

        return metricService.getMetricsByDateRange(from, to);

    }

}
