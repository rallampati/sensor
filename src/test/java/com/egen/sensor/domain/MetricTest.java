package com.egen.sensor.domain;

import com.egen.sensor.rules.RulesExecutor;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by ram on 11/28/17.
 */
public class MetricTest {

    @Test
    public void createMetric(){

        Metric metric = new Metric(new Date(), 155);
        assertThat(metric.getValue()).isEqualTo(155);
    }

    @Test
    public void isItOverWeightMetric(){

        Metric metric = new Metric(new Date(), 166);
        RulesExecutor.executeRules(metric);
        assertThat(metric.isOverWeight()).isEqualTo(true);
    }

    @Test
    public void isItUnderWeightMetric(){

        Metric metric = new Metric(new Date(), 134);
        RulesExecutor.executeRules(metric);
        assertThat(metric.isUnderWeight()).isEqualTo(true);
    }

    @Test
    public void isMetricWithInTheRange(){

        Metric metric = new Metric(new Date(), 151);
        RulesExecutor.executeRules(metric);
        assertThat(metric.isUnderWeight()).isEqualTo(false);
        assertThat(metric.isOverWeight()).isEqualTo(false);
    }
}