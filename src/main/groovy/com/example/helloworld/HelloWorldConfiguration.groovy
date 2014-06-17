package com.example.helloworld

import com.example.helloworld.core.Template
import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.Valid
import javax.validation.constraints.NotNull

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    String template;

    @NotEmpty
    String defaultName = "Stranger";

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory
    }
}
