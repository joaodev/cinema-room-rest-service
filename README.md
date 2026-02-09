# Cinema Room REST Service

Spring Boot REST API project for a cinema room domain. This repository currently contains the application scaffold and build configuration; REST endpoints are not implemented yet.

## Overview

- Purpose: provide a clean base for a cinema room REST service (booking, availability, and related workflows).
- Current state: Spring Boot app with web starter and Gradle build; no controllers yet.

## Requirements

- Java 25 (toolchain configured in `build.gradle`)
- Gradle (wrapper included)

## Project Layout

```
.
├── build.gradle
├── settings.gradle
├── src
│   └── main
│       └── resources
│           └── application.yaml
└── README.md
```

## Getting Started

Clone and enter the project:

```bash
git clone <repository-url>
cd cinema-room-rest-service
```

Build the project:

```bash
./gradlew build
```

Run the application:

```bash
./gradlew bootRun
```

The server starts at `http://localhost:8080`.

## Running Tests

```bash
./gradlew test
```

## Configuration

Spring Boot configuration lives in `src/main/resources/application.yaml`.

## API Endpoints

Base URL: `http://localhost:8080`

### `GET /seats`

Returns the cinema room size and the list of currently available seats.

Response `200`:

```json
{
  "rows": 9,
  "columns": 9,
  "seats": [
    {
      "row": 1,
      "column": 1,
      "price": 10,
      "available": true
    }
  ]
}
```

### `POST /purchase`

Purchases a seat and returns a token plus the ticket details.

Request body:

```json
{
  "row": 3,
  "column": 4
}
```

Response `200`:

```json
{
  "token": "8d2c4a1b-6f5d-4a2d-9c2a-1b5c2a9a2f44",
  "ticket": {
    "row": 3,
    "column": 4,
    "price": 10
  }
}
```

Errors:

- `400` with `{"error":"The number of a row or a column is out of bounds!"}` when row/column is outside 1..9.
- `400` with `{"error":"The ticket has been already purchased!"}` when the seat is no longer available.

### `POST /return`

Returns a previously purchased ticket by token.

Request body:

```json
{
  "token": "8d2c4a1b-6f5d-4a2d-9c2a-1b5c2a9a2f44"
}
```

Response `200`:

```json
{
  "ticket": {
    "row": 3,
    "column": 4,
    "price": 10
  }
}
```

Errors:

- `400` with `{"error":"Wrong token!"}` when the token is not found.

### `GET /stats`

Returns aggregate stats for purchases.

Response `200`:

```json
{
  "income": 30,
  "available": 81,
  "purchased": 3
}
```

## Pricing and Rules

- Room size is fixed at 9 rows x 9 columns.
- Seat pricing: rows 1-4 cost 10; rows 5-9 currently cost 0 (as implemented).

## Tech Stack

- Java 25
- Spring Boot 4 (web starter)
- Gradle

## Contributing

Fork the repository, create a feature branch, and open a pull request with a clear description.

## License

MIT License.
