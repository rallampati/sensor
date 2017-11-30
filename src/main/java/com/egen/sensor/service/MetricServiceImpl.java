package com.egen.sensor.service;

import com.egen.sensor.domain.Alert;
import com.egen.sensor.domain.Metric;
import com.egen.sensor.repository.AlertRepository;
import com.egen.sensor.repository.MetricRepository;
import com.egen.sensor.rules.RulesExecutor;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by ram on 11/28/17.
 */
@Service
public class MetricServiceImpl implements MetricService {

    private MetricRepository metricRepository;

    private AlertRepository alertRepository;

    public MetricServiceImpl(MetricRepository metricRepository, AlertRepository alertRepository) {
        this.metricRepository = metricRepository;
        this.alertRepository = alertRepository;
    }

    /**
     * @param metric
     */
    @Override
    public void save(Metric metric) {

        RulesExecutor.executeRules(metric);
        metricRepository.save(metric);

        Alert alert = generateAlert(metric);
        if (alert != null) {
            alertRepository.save(alert);
        }

    }

    /**
     * @return
     */
    @Override
    public List<Metric> getAllMetrics() {

        return metricRepository.findAll();

    }

    /**
     * @param from
     * @param to
     * @return
     */
    @Override
    public List<Metric> getMetricsByDateRange(Date from, Date to) {

        return metricRepository.findAllByDateRange(from, to);

    }

    /**
     *
     * @param metric
     * @return
     */
    protected Alert generateAlert(Metric metric) {
        Alert alert = null;
        //Create an alert.
        if (metric.isOverWeight()) {
            alert = new Alert(new Date(), "Over weight alert!!");
        } else if (metric.isUnderWeight()) {
            alert = new Alert(new Date(), "Over weight alert!!");
        }
        return alert;
    }
}
