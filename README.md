# Hello Spring Boot

## Overview

This is a simple web application based on *Spring Boot*. It showcases features such as *Spring Boot Web*, *Spring Boot Data JPA*, *Spring Boot Mail*, *Spring Boot Security*, *Thymeleaf*, *H2 Database*, *Selenium*, *Cucumber*.

## Dependencies

### Runtime dependencies

*[Spring Boot](http://projects.spring.io/spring-boot/)*: Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

* *Web*: Spring Boot package for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded container.

* *Data JPA*: Spring Boot Data JPA with Hibernate package to store and retrieve data in a relational database. 

* *Security*: If Spring Security is on the classpath, the Spring Boot automatically secures all HTTP endpoints with "basic" authentication. But you can further customize the security settings.

* *Mail*: Spring Boot Mail package to send emails.

* *Actuator*: Spring Boot Actuator includes a number of additional features to help you monitor and manage your application when it’s pushed to production.

* *DevTools*: The aim of this module is to try and improve the development-time experience when working on Spring Boot applications.

*[H2 Database](http://www.h2database.com/)*: H2, the Java SQL database.

*[Thymeleaf](http://www.thymeleaf.org/)*: Thymeleaf is a modern server-side Java template engine for both web and standalone environments.

*[jQuery](https://jquery.com/)*: *jQuery* is a cross-platform JavaScript library designed to simplify the client-side scripting of HTML.

*[Bootstrap](http://getbootstrap.com/)*: HTML, CSS and JS framework for developing responsive, mobile first projects on the web.

### Dev dependencies

*[gradle](https://gradle.org/)*: Gradle is an open source build automation system that builds upon the concepts of Apache Ant and Apache Maven and introduces a Groovy-based domain-specific language (DSL) instead of the XML form used by Apache Maven of declaring the project configuration.

### Test dependencies

*[Selenium](http://www.seleniumhq.org/)*: Selenium automates browsers. Primarily, it is for automating web applications for testing purposes, but is certainly not limited to just that.

*[Chrome](https://www.google.com/chrome/)*: Browser.

* *[Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/getting-started)*: ChromeDriver is a separate executable that WebDriver uses to control Chrome.

*[Cucumber](https://cucumber.io/), [Gherkin](https://github.com/cucumber/cucumber/wiki/Gherkin)*: Cucumber is a tool that supports Behaviour-Driven Development (BDD) - a software development process that aims to enhance software quality and reduce maintenance costs. Gherkin is the language that Cucumber understands. It is a Business Readable, Domain Specific Language that lets you describe software’s behaviour without detailing how that behaviour is implemented.

## How to

### Setup

The following software should be installed on your computer.

* Java

* gradle

* Chrome and Chrome Driver

To be able to start the application you need to setup your own `src/main/resources/application.properties` file based on the `src/main/resources/application.properties.sample` file.

To be able to start the smoke test you need to setup your own `src/test/resources/local.properties` file based on the `src/test/resources/local.properties.sample` file. Make sure that you specify the paths to the selenium chrome driver.

### Running the application

To run the web application in place without building a jar first you can use the command `gradle --daemon bootRun`. This will start the web application with the specified *port* and *debug port* located in the *build.gradle* file.

If you made some changes to the HTML or CSS files then you don't need to restart the application. If you make some changes to the Java code then simply building the project with `gradle build` while the application is still running won't automatically restart it with the changes. This *Spring Boot DevTools* feature is disabled in *gradle.build*. So if you make some changes in the Java Source code then you need to shut down and start the application manually.

### Testing

To run the Selenium tests just run the command `gradle --daemon smokeTest`. The browser used for testing is Chrome. The test itself will not start the application, it should be started before.

Properties for testing:

* `hellospring.url`: The url of the running application against which the UI tests will be executed.

* `hellospring.user.name`: The name of the user which will be used in UI tests for authentication.

* `hellospring.user.password`:  The password of the user which will be used in UI tests for authentication.

* `hellospring.chrome.driver`: The path to the WebDriver binary for the Chrome browser (containing the name of the binary itself).

* `hellospring.test.env`: The test env which can be used to easily switch between properties. Default is `local` thus by default the UI tests will use `local.properties`.

### H2 Database

The application uses an in-memory H2 Database. 

#### H2 Console

You can use the `/h2-console` url to connect to the H2 database used by the web application. You should use the following data to log in.

* Driver Class: org.h2.Driver

* JDBC URL: jdbc:h2:mem:testdb

* User Name: sa

* Password is empty.

Note that to have this url working I had to make a slight modification in the `src/main/java/hu/learnerbot/hellospring/config/CustomWebSecurityConfigurerAdapter.java` file.

#### Preloaded data

With the help of the `src/main/resources/data.sql` there's preloaded data.

The following users are already in the database.

| user name  | email             | password (hashed) | role  |
| ---------- | ----------------- | ----------------- | ----- |
| Test Admin | admin@example.com | 123456            | ADMIN |
| Test User  | user@example.com  | 123456            | USER  |

### Web application shutdown

With the help of the Spring Boot Actuator you can shutdown the web application if you send a POST to the `/actuator/shutdown`.

For example: `curl -X POST http://localhost:11001/actuator/shutdown`. 

Note that to have this url working I had to make a slight modification in the `src/main/java/hu/learnerbot/hellospring/config/CustomWebSecurityConfigurerAdapter.java` file.

### Pitfalls

#### Chrome does not load

This might be because the path to the driver is not set properly.

Chrome driver might not support the version of Chrome. In newer versions the Chrome driver version should match the version of Chrome.
