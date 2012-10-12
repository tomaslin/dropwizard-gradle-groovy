# Dropwizard + Gradle = &hearts;

Minimal example of getting Dropwizard going with Gradle (instead of Maven).

Because the only thing I hate more than Java is XML.

## Gotchas

You need Gradle 1.1 or higher, otherwise you'll run into a [dependency resolution bug](http://issues.gradle.org/browse/GRADLE-2285).

## FatJar

This example is using the [Gradle FatJar Plugin](https://github.com/musketyr/gradle-fatjar-plugin) which will create
a JAR file of the project including all dependencies, similar to the [Maven Assembly Plugin](http://maven.apache.org/plugins/maven-assembly-plugin/)
or the [Maven Shade Plugin](http://maven.apache.org/plugins/maven-shade-plugin/).

To create a fat JAR just run `gradle fatJar`. The resulting JAR will be saved as `./build/libs/dropwizard-gradle-fat.jar`.

## Gradle Application Plugin

An alternative to creating a fat JAR is using the [Gradle Application Plugin](http://www.gradle.org/docs/current/userguide/application_plugin.html).

To create a distributable ZIP archive including all dependencies for your application just run `gradle distZip`. The
resulting archive will be saved as `./build/distributions/dropwizard-gradle.zip`.

You can also use the `run` task to start the application.