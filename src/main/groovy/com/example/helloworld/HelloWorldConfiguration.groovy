package com.example.helloworld;

import com.example.helloworld.core.Template;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    String template;

    @NotEmpty
    String defaultName = "Stranger";

    @Valid
    @NotNull
    @JsonProperty("database")
    DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

}
