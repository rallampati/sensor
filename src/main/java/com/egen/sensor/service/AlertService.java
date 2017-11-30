package com.egen.sensor.service;

import com.egen.sensor.domain.Alert;
import com.egen.sensor.domain.Metric;
import java.util.Date;
import java.util.List;

/**
 * Created by ram on 11/28/17.
 */
public interface AlertService {

  List<Alert> getAllAlerts();
  List<Alert> getAlertsByDateRange(Date from, Date to);
}
