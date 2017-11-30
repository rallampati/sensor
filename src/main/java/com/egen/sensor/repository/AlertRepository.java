package com.egen.sensor.repository;

import com.egen.sensor.domain.Alert;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * we can create generic repository interface but I think its a overkill for this problem.
 * Created by ram on 11/29/17.
 */
@Component
public class AlertRepository {

    private Datastore datastore;

    public AlertRepository(Datastore datastore){
        this.datastore = datastore;
    }

    /**
     *
     * @return
     */
    public List<Alert> findAll() {

        final Query<Alert> query = datastore.createQuery(Alert.class);
        return query.asList();

    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public List<Alert> findAllByDateRange(Date from, Date to) {

        final Query<Alert> query = datastore.createQuery(Alert.class);
        query.and(query.criteria("created_time").greaterThanOrEq(from),
                query.criteria("created_time").lessThanOrEq(to));
        return query.asList();

    }

    /**
     *
     * @param alert
     */
    public void save(Alert alert) {

        datastore.save(alert);
    }
}
