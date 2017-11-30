package com.egen.sensor.service;

import com.egen.sensor.domain.Alert;
import com.egen.sensor.domain.Metric;

import java.util.Date;
import java.util.List;

import com.egen.sensor.repository.AlertRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by ram on 11/28/17.
 */
@Service
public class AlertServiceImpl implements AlertService {

    private AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public List<Alert> getAllAlerts() {

        return alertRepository.findAll();
    }

    @Override
    public List<Alert> getAlertsByDateRange(Date from, Date to) {

        return alertRepository.findAllByDateRange(from, to);

    }

}
