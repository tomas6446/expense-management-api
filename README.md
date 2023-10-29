# Impensa WebAPI

This repository contains the backend implementation for [Impensa](https://github.com/richard96292/impensa).

## Prerequisites
- Java 8 or above
- Maven

## Development Setup & Installation

```bash
# Create a new directory for Impensa and navigate into it
# The client can be cloned to the same directory
mkdir impensa
cd impensa

# Clone the server repository
git clone https://github.com/tomas6446/impensa-server-spring
cd impensa-server-spring

# Run postgres container
docker compose up -d

# Run project
mvn spring-boot:run
```

After running these commands, you should be able to access the API at `http://localhost:8080`.

## License

This project is free software and is distributed under the [AGPL (GNU Affero General Public License)](https://www.gnu.org/licenses/agpl-3.0.en.html).
