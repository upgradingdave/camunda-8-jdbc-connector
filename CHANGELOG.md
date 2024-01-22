# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

The version of this project reflects compatability with the version of [Camunda Connector SDK](https://github.com/camunda/connectors).

### Unreleased

- Add support for Microsoft SQL Server (mssql)

## 8.4.0-SNAPSHOT (January 2024)

### Added
- Added Spring Boot Profiles for h2
- Added comprehensive bpmn process diagrams to demonstrate connecting to h2
- Unit tests run successfully for h2
- Added Spring Boot Profiles for mysql
- Added comprehensive bpmn process diagrams to demonstrate connecting to mysql
- Unit tests run successfully for mysql
- Added Spring Boot Profiles for postgres
- Added comprehensive bpmn process diagrams to demonstrate connecting to postgres
- Unit tests run successfully for postgres

### Changed
- Updated this project to be compatible with the `8.4.0-SNAPSHOT` Camunda Connectors sdk

### Fixed
- Improved [DatabaseManager](./src/main/java/io/camunda/connector/db/DatabaseManager.java) code by closing connections, result sets and prepared statements correctly. 


## 0.1.0-SNAPSHOT (January 2023)

### Added
- First working prototype of a Community jdbc connector.
- Written against 0.4.0 of the Camunda connectors sdk
