# Number Classification API

A simple API that checks if a number is prime, perfect, armstrong, odd/even, sums its digits, and fetches a math fact. Perfect for quick number fun or just feeding your curiosity.

---

## Quick Start

### Requirements
1. [Java 21](https://www.oracle.com/java/technologies/downloads/)
2. [Maven 3.8+](https://maven.apache.org/download.cgi)

### Installation
1. **Clone** the repo:
   ```bash
   git clone https://github.com/NneomaBlessyn/number-classification.git
2. **Go into the project folder:**
    ```bash
    cd number-classification
3. **Build it:**
    ```bash
    mvn clean install
4. **Run it:**
    ```bash
    mvn spring-boot:run
5. By default, it listens on port 8098 (set in application.properties), so the base URL is http://localhost:8098/.

---

### Using the API
**Endpoint**
```bash
GET /api/classify-number?number={integer}
```
* Query Param: number (must be an integer, e.g. -5, 42, or 371).

**Example**
```bash
GET http://localhost:8098/api/classify-number?number=371
```

**Successful Response (HTTP 200)**
```json
{
  "number": 371,
  "is_prime": false,
  "is_perfect": false,
  "properties": [
    "armstrong",
    "odd"
  ],
  "digit_sum": 11,
  "fun_fact": "371 is a narcissistic number."
}
```
**Error Response (HTTP 400)**
```json
{
  "number": "alphabet",
  "error": true
}
```

---
## Swagger (API Docs)
Visit Swagger UI at:
```bash
http://localhost:8098/swagger-ui/index.html
```

---
## Deployment
The API is deployed on Heroku at:
```bash
https://number-classification-4790cae77305.herokuapp.com
```

Classify a number (Example 371):
```bash
https://number-classification-4790cae77305.herokuapp.com/api/classify-number?number=371
```

Swagger UI:
```bash
https://number-classification-4790cae77305.herokuapp.com/swagger-ui/index.html
```
