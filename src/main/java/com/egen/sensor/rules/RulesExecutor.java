package com.egen.sensor.rules;

import static org.jeasy.rules.core.RulesEngineBuilder.aNewRulesEngine;

import com.egen.sensor.domain.Metric;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;

/**
 * Created by ram on 11/28/17.
 */
public class RulesExecutor {

    public static final int BASE_WEIGHT = 150; //Shouldn't this be tied to a device or a user entity?

    /**
     * Couldn't find helpful documentation on the easy rules api. Not sure if this API is still active
     * or not and I'm not quite sure if Rules engine is thread safe either.
     *
     * @param metric
     */
    public static void executeRules(Metric metric) {
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new OverWeight(metric));
        rules.register(new UnderWeight(metric));

        RulesEngine rulesEngine = aNewRulesEngine().build();
        rulesEngine.fire(rules, facts);
    }

}
