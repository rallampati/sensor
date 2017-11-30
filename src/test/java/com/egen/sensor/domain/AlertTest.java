package com.egen.sensor.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

/**
 * Created by ram on 11/28/17.
 */
public class AlertTest {

    @Test
    public void createAlert() {

        Alert alert = new Alert(new Date(), "Over weight alert");
        assertThat(alert.getAlertMessage()).isEqualTo("Over weight alert");

    }

}