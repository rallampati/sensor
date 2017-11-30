package com.egen.sensor.repository;

import com.egen.sensor.domain.Metric;
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
public class MetricRepository {

    private Datastore datastore;

    public MetricRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    /**
     *
     * @param metric
     */
    public void save(Metric metric) {

        datastore.save(metric);
    }

    /**
     *
     * @return
     */
    public List<Metric> findAll() {

        final Query<Metric> query = datastore.createQuery(Metric.class);
        return query.asList();

    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public List<Metric> findAllByDateRange(Date from, Date to) {

        final Query<Metric> query = datastore.createQuery(Metric.class);
        query.and(query.criteria("timeStamp").greaterThanOrEq(from),
                query.criteria("timeStamp").lessThanOrEq(to));
        return query.asList();

    }
}
