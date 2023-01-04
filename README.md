[![Community Extension](https://img.shields.io/badge/Community%20Extension-An%20open%20source%20community%20maintained%20project-FF4700)](https://github.com/camunda-community-hub/community)
[![](https://img.shields.io/badge/Lifecycle-Proof%20of%20Concept-blueviolet)](https://github.com/Camunda-Community-Hub/community/blob/main/extension-lifecycle.md#proof-of-concept-)
![Compatible with: Camunda Platform 8](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%208-0072Ce)

# JDBC Connector Template

!!! Work in progress !!!

This is the very beginning of an attempt at developing a Camunda 8 Connector that is capable of connecting to Databases via JDBC and running SQL commands.

At the moment it doesn't do much other than select from an empty, in-memory, H2 Database. 

# Overview



This connector maintains a simple in-memory cache of [DataSources](https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html). One `DataSource` for each unique database configuration. 

Each time a process instance arrives at a JDBC Connector Task, the [JdbcConnectorFunction](src/main/java/io/camunda/connector/JdbcConnectorFunction.java) checks the in-memory cache for a matching DataSource. If the cache contains a DataSource that matches exactly to the db configuration inside the [JdbcConnectorRequest](src/main/java/io/camunda/connector/JdbcConnectorFunction.java), then the DataSource is reused. Otherwise, a new DataSource is created using the db connection configuration found inside the  `JdbcConnectorRequest` as defined in the bpmn process diagram. 

In other words, if a process has multiple JDBC Connector tasks that all share same database connection configuration, a single Datasource is created and reused.

This also means that if a process has multiple JDBC Connector tasks that each connect slightly differently, a separate DataSource is created for each configuration.

## Supported Database Types

As of now, this project supports the following types:

- [H2](src/main/java/io/camunda/connector/db/H2Database.java)
- [Postgresql](src/main/java/io/camunda/connector/db/PostgresDatabase.java)

## More Design Details

The [Database](src/main/java/io/camunda/connector/db/Database.java) interface is implemented for each type of database that this project supports.

The `Database.`

# TODO / Next steps

// I Think for now I'll simplify things by having only a jdbcurl as part of the jdbcparams.
// later it will be possible to build custom element templates for different db flavors by creating custom JdbcParam classes

- simplify element template to accept only jdbc url
- Implement prepared statements by passing json map structure as params
- write h2 unit tests
- Configure Output variable.
- real world test against postgresql
- Generate custom svg image for this connector and update the template-connector.json
- Configure password as a SECRET
- Implement options for connection pooling?
- Create separate element templates for each type of db?
- will also need separate JDBCParam classes for each type of db?

## Build

You can package the Connector by running the following command:

```bash
mvn clean package
```

This will create the following artifacts:

- A thin JAR without dependencies.
- An uber JAR containing all dependencies, potentially shaded to avoid classpath conflicts. This will not include the SDK artifacts since those are in scope `provided` and will be brought along by the respective Connector Runtime executing the Connector.

### Shading dependencies

You can use the `maven-shade-plugin` defined in the [Maven configuration](./pom.xml) to relocate common dependencies
that are used in other Connectors and the [Connector Runtime](https://github.com/camunda-community-hub/spring-zeebe/tree/master/connector-runtime#building-connector-runtime-bundles).
This helps avoiding classpath conflicts when the Connector is executed. 

Use the `relocations` configuration in the Maven Shade plugin to define the dependencies that should be shaded.
The [Maven Shade documentation](https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation.html) 
provides more details on relocations.

## API

### Input

```json
{
  "token": ".....",
  "message": "....."
}
```

### Output

```json
{
  "result": {
    "myProperty": "....."
  }
}
```

### Error codes

| Code | Description |
| - | - |
| FAIL | Message starts with 'fail' (ignoring case) |

## Test locally

Run unit tests

```bash
mvn clean verify
```

### Test with local runtime

Use the [Camunda Connector Runtime](https://github.com/camunda-community-hub/spring-zeebe/tree/master/connector-runtime#building-connector-runtime-bundles) to run your function as a local Java application.

In your IDE you can also simply navigate to the `LocalContainerRuntime` class in test scope and run it via your IDE.
If necessary, you can adjust `application.properties` in test scope.

## Element Template

The element templates can be found in the [element-templates/template-connector.json](element-templates/jdbc-connector.json) file.
