package com.egen.sensor.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

/**
 * Created by ram on 11/28/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(value = "metrics", noClassnameStored = true)
public class Metric {


    @Id
    private ObjectId id;

    @Property("created_time")
    private Date timeStamp;

    private int value;

    @Transient
    private boolean underWeight;

    @Transient
    private boolean overWeight;

    public Metric(Date timeStamp, int value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }
}
