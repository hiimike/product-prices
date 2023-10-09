# Product Prices Service

This service manages product prices for an e-commerce platform.

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Setup](#setup)
- [Usage](#usage)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Description

The Product Prices Service is responsible for retrieving product prices. It ensures that the correct price is applied based on provided date time, product ID, and brand ID.

## Technologies

- **Spring Boot**: For creating the standalone, production-grade Spring-based application.
- **JPA & Hibernate**: For ORM and database interaction.
- **H2 Database**: In-memory database used for development and testing.
- **Liquibase**: For database migrations and schema management.
- **JUnit**: For unit testing.
- **Testcontainers**: For integration testing with databases.

## Setup

1. Clone the repository:
    ```bash
    git clone git@github.com:hiimike/product-prices.git
    cd product-prices
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the service:
    ```bash
    mvn spring-boot:run
    ```

## Usage

Provide details on how to use the API or interact with the service. For instance:

- Fetching a price:
    ```http
    GET /api/v1/price/35455?date=2020-06-14T10:00&brand_id=1
    ```

## Testing

1. To run unit tests:
    ```bash
    mvn test
    ```

2. To run integration tests:
    ```bash
    mvn verify
    ```
