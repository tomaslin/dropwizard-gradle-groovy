package com.example.helloworld

import com.example.helloworld.auth.ExampleAuthenticator
import com.example.helloworld.cli.RenderCommand
import com.example.helloworld.core.Person
import com.example.helloworld.core.Template
import com.example.helloworld.core.User
import com.example.helloworld.db.PersonDAO
import com.example.helloworld.health.TemplateHealthCheck
import com.example.helloworld.resources.HelloWorldResource
import com.example.helloworld.resources.PeopleResource
import com.example.helloworld.resources.PersonResource
import com.example.helloworld.resources.ProtectedResource
import com.google.common.collect.ImmutableList
import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.auth.basic.BasicAuthProvider
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.hibernate.SessionFactoryFactory
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args)
    }

    final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
        new HibernateBundle<HelloWorldConfiguration>(ImmutableList.copyOf([Person]), new SessionFactoryFactory()) {
            @Override
            DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory()
            }
        }

    @Override
    String getName() {
        return "hello-world"
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addCommand(new RenderCommand())
        bootstrap.addBundle(new AssetsBundle())
        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory()
            }
        })
        bootstrap.addBundle(hibernateBundle)
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory())

        environment.jersey().register(new BasicAuthProvider<User>(new ExampleAuthenticator(), "SUPER SECRET STUFF"))

        final Template template = configuration.buildTemplate()

        environment.healthChecks().register("template", new TemplateHealthCheck(template))
        environment.jersey().register(new HelloWorldResource(template))
        environment.jersey().register(new ProtectedResource())
        environment.jersey().register(new PeopleResource(dao))
        environment.jersey().register(new PersonResource(dao))
    }
}
