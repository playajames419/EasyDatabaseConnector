# EasyDatabaseConnector

Easily access your database in many plugins with minimal configuration

## Description

This API allows developers to access a database across multiple plugins by simply adding the API as a dependency to their projects. Configure once, use on any project!

## Features

* Object oriented database access using [Jooq](https://www.jooq.org/)
* Connection pool and cache management using [HikariCP](https://github.com/brettwooldridge/HikariCP)
* Very efficient
* Safe and secure
* Lightweight

## Getting Started

### Dependencies

* Spigot/Paper/etc API or similar
* MySQL database

## Setup

* Fork or clone this repository
* Modify the pom.xml with your database details
  ```xml
  <url>jdbc:mysql://localhost:3306/database-name</url>      <!-- Put database host:port/database-name here -->
  <user>username</user>                                     <!-- Put database username here -->
  <password>password</password>                             <!-- Put database password here -->
  
  <inputSchema>database-name</inputSchema>                            <!-- Put database name here -->
  ```
  
### Generating Jooq Classes

> Note: Jooq uses a plugin to generate classes that allow you to access your database. Any time you make changes to your database structure(Add tables, modify colunms, etc.) You will need to regenerate these classes.

This [Jooq Code Generation Manual](https://www.jooq.org/doc/latest/manual/getting-started/tutorials/jooq-in-7-steps/jooq-in-7-steps-step3/) will show you how to generate code.

### Generate Jooq Classes Using InteliJ

* Open your maven tool window
* Expand for Plugins section
* Expand jooq-codegen section
* Double click jooq-codegen:generate
* Build the project

### Accessing Your Database

* Add the compiled project as a dependency to your project
* You now have access to [HikariCP](https://github.com/brettwooldridge/HikariCP) and [Jooq](https://www.jooq.org/) classes.

#### Using Jooq with HikariCP
* Create a DSLContext variable
```java
DSLContext context = DSL.using(HikariCPFactory.getDataSource(), SQLDialect.MYSQL);
```
* Check out [this class](https://github.com/playajames419/FlagsLib/blob/master/src/main/java/me/playajames/flagslib/flagslib/FlagsDBDAO.java) for some example usages of the context object.
* Refer to the [Jooq Manual](https://www.jooq.org/learn/)

## Help

Contact me on Discord(Playajames#5723) or [create an issue](https://github.com/playajames419/EasyDatabaseConnector/issues/new).

## Authors

Contributors names and contact info

  * playajames(Discord: Playajames#5723)

## Version History

* 1.0-SNAPSHOT
    * Initial Release

## License

This project is licensed under the GPL-3.0 License.

## Acknowledgments

Inspiration, code, snippets, etc.
* [PaperMC](https://papermc.io/)
* [Jooq](https://www.jooq.org/)
* [HikariCP](https://github.com/brettwooldridge/HikariCP)
* [MySQL Connector Java](https://github.com/mysql/mysql-connector-j)
