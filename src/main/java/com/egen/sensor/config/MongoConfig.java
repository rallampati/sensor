package com.egen.sensor.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ram on 11/28/17.
 */
@Configuration
public class MongoConfig {

    @Autowired
    private MongoProperties mongoProperties;

    /**
     * @return
     */
    private Morphia morphia() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.egen.sensor.domain");
        return morphia;
    }

    /**
     * This will return the data store for mongo - Kind of a generic repository??
     *
     * @param mongoClient
     * @return
     */
    @Bean
    public Datastore datastore(MongoClient mongoClient) {

        final Datastore datastore = morphia()
                .createDatastore(mongoClient, mongoProperties.getDatabase());
        datastore.ensureIndexes();

        return datastore;
    }
}
