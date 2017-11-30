package com.egen.sensor.controller;

import com.egen.sensor.domain.Alert;
import com.egen.sensor.service.AlertService;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ram on 11/29/17.
 */
@RestController
@RequestMapping(value = "alert", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlertController {

    private AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    /**
     * @return
     */
    @GetMapping("getAllAlerts")
    public List<Alert> getAllAlerts() {

        return this.alertService.getAllAlerts();

    }

    /**
     * @param from
     * @param to
     * @return
     */
    @GetMapping("getAlertsByDateRange")
    public List<Alert> getMetricsByDateRange(
            @RequestParam("from") @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss") Date to) {

        return alertService.getAlertsByDateRange(from, to);

    }

}
