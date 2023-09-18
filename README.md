# Automated Tests

This framework is an end-to-end testing for an **e-commerce** website using Java/Selenium.

## Technologies

- **[Java 8](https://openjdk.java.net/projects/jdk8/)** 
- **[Maven](https://maven.apache.org/download.cgi)**
- **[Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)**


## Environments
Setting __environments__:

### Browsers

- Chrome _(default)_:
  ```mvn clean test -Dbrowser=chrome```

- Firefox:
  ```mvn clean test -Dbrowser=firefox```

- Edge:
  ```mvn clean test -Dbrowser=edge```



### Hosts

- Local _(default)_:
  ```mvn clean test -Dhost=host.localhost```

- Selenium Grid:
  ```mvn clean test -Dhost=host.docker.selenium.grid```


### Headless Mode

- ```mvn clean test -Dheadless=true```


## Running tests

- Installing all dependencies

```
mvn clean install
```

- Run all tests sequentially

```
mvn clean test
```

- Run all tests passing all parameters by command line

```
mvn clean test -Dbrowser=chrome -Dhost=host.localhost -Dheadless=false
```

- Parallel execution

Set the ```junit.jupiter.execution.parallel.enabled``` configuration parameter to ```true``` in the __src/main/resources/junit-platform.properties__ file


### Running the tests using _Selenium Grid_

- Build docker-compose

```
docker-compose up
```

- Launch selenium grid interface

```
http://localhost:4444
```

- Set the __host__ parameter to `host.docker.selenium.grid`

  ```mvn clean test -Dhost=host.docker.selenium.grid```
