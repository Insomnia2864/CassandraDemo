package com.example.cassandrademo.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {
    @Value(value = "${spring.data.cassandra.keyspace-name:}")
    String keyspaceName;
    @Value(value = "${spring.data.cassandra.schema-action}")
    SchemaAction schemaAction;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return schemaAction;
    }
}
