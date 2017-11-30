package com.egen.sensor.rules;

import static com.egen.sensor.rules.RulesExecutor.BASE_WEIGHT;

import com.egen.sensor.domain.Metric;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

/**
 * Created by ram on 11/28/17.
 */
@Rule(name = "Under weight rules", description = "if the value is 10% less than base weight")
public class UnderWeight {

    private Metric metric;

    public UnderWeight(Metric metric) {
        this.metric = metric;
    }

    @Condition
    public boolean isOverWeight() {
        return metric.getValue() < BASE_WEIGHT - (int) (BASE_WEIGHT * 10) / 100;
    }

    @Action
    public void setAlertFlag() {
        System.out.printf("Alert has been generated for %s.\n", metric.getValue());
        metric.setUnderWeight(true);
    }
}
