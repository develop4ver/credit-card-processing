# Credit Card Processing Assignment

This repository implements a simple credit card processing system as described in the assignment. It consists of a Spring Boot RESTful API and an integrated React user interface. The application allows you to add new credit card accounts and view all cards in a list. Card numbers are validated using the Luhn 10 algorithm.

## Backend (Spring Boot)

The backend is a standalone Spring Boot project using Maven for dependency management. It exposes two REST endpoints:

| Method | Endpoint | Description |
|-------:|:---------|:------------|
| `POST` | `/cards` | Creates a new credit card account. Accepts a JSON body with `name`, `cardNumber` and `limit`. Performs input validation and a Luhn check. Returns the created card on success or a 400 status with an error message on failure. |
| `GET`  | `/cards` | Returns all credit card accounts currently stored in memory. |

Cards are stored in an in‑memory map keyed by the account holder’s name. Card numbers failing the Luhn check are rejected and not stored. New cards start with a £0.00 balance.

### Running the API

1. **Install Java 17** if it isn’t already installed. The project uses Java 17.
2. Ensure you have **Maven** available (e.g. `mvn -v` shows a version). If not, install Maven.
3. Navigate to the `credit-card-processing/backend` directory.
4. Run the application using Maven:

   ```bash
   mvn spring-boot:run
   ```

   By default the API will start on port `8080`. You should see output indicating that the Tomcat server has started.

### API Usage Examples

Use `curl` or any HTTP client:

Add a valid card:

```bash
curl -X POST http://localhost:8080/cards \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice", "cardNumber": "4111111111111111", "limit": 2000}'
```

Attempt to add an invalid card (fails Luhn):

```bash
curl -X POST http://localhost:8080/cards \
  -H "Content-Type: application/json" \
  -d '{"name": "Bob", "cardNumber": "123456789012", "limit": 1000}'
```

List all cards:

```bash
curl http://localhost:8080/cards
```

## Frontend (React)

The React user interface is served automatically by the Spring Boot application. It lives in `src/main/resources/static/index.html`. When you run the API as described above, navigate to [http://localhost:8080](http://localhost:8080) to interact with the UI. The interface mirrors the provided wireframe: it includes a form to add cards and a table showing existing cards. Validation errors are displayed inline.

### Testing with Dummy Data

Here are some sample scenarios to exercise the full stack:

1. **Valid card**
   - **Name**: `Charlie`
   - **Card number**: `5555555555554444` (a common MasterCard test number)
   - **Limit**: `1500`
   - Expected result: the card appears in the table with a £0.00 balance.

2. **Invalid card number**
   - **Name**: `Dave`
   - **Card number**: `1234 5678 9012 3456`
   - **Limit**: `500`
   - Expected result: the form shows “Invalid card number.” and the card is not added.

3. **Duplicate name**
   - First add a card for **Emily** with a valid number.
   - Then attempt to add another card also named **Emily**. The server will reject the request with “A card with that name already exists.”

4. **Invalid limit**
   - Enter a non‑numeric or negative limit value. The form will display “Limit must be positive.” and prevent submission.

## Unit Tests

The project includes a small suite of JUnit tests (`CardServiceTest`) under `src/test/java`. These tests verify the Luhn algorithm, adding cards and duplicate name detection. You can run the tests with:

```bash
mvn test
```

Additional tests (e.g. controller integration tests) can be added to further demonstrate testing practices.

## Summary

This solution hand‑codes the Luhn algorithm, implements a simple in‑memory persistence layer and exposes a minimal REST API. The integrated React UI uses `fetch` to communicate with the backend and reflects the provided wireframe. Feel free to extend the functionality (e.g. adding charge/credit operations, persisting to a database) as an exercise.