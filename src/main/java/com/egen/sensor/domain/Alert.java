package com.egen.sensor.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by ram on 11/28/17.
 * <p>
 * Since the Alert domain
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(value = "alerts", noClassnameStored = true)
public class Alert {
    @Id
    private ObjectId id;

    private Date created_time;

    private String alertMessage;

    public Alert(Date created_time, String alertMessage) {
        this.created_time = created_time;
        this.alertMessage = alertMessage;
    }

}
