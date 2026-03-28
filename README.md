Here is the complete `README.md` for your **PaymentService**, structured exactly like your ProductService example and tailored to the code you provided.

-----

# PaymentService Microservices

## Overview

PaymentService is a core component of the ShoppingCart system responsible for processing transactions and managing payment records. It handles payment creation (processing) and retrieval of payment details linked to specific orders. The system uses MySQL for persistent storage and is designed to be easily portable to other relational databases.

# High Level Design
![App Dashboard](./HLD_new.png)

## Tech Stack

  - **Framework:** Spring Boot 3.2.2
  - **Language:** Java 17
  - **Database:** MySQL
  - **ORM:** Spring Data JPA / Hibernate
  - **Build Tool:** Maven
  - **Logging:** Log4j2
  - **Code Generation:** Lombok

## Features Implemented

1.  **Payment Processing**

      - Process a new payment against an order with specific payment modes (CASH, PAYPAL, DEBIT\_CARD, CREDIT\_CARD).

2.  **Payment Retrieval**

      - Fetch detailed payment information using a specific Order ID.

3.  **Error Handling**

      - Custom exception handling for payment failures or missing records.
      - Standardized error responses with specific error codes.

## Prerequisites

  - Java (JDK 17 or later)
  - Maven
  - MySQL Server

## Setup & Running the Application

1.  **Clone the Repository:**

    ```sh
    git clone https://github.com/yourusername/PaymentService.git
    cd PaymentService
    ```

2.  **Build the Application:**

    ```sh
    ./mvnw clean install
    ```

3.  **Run the Application:**

    ```sh
    ./mvnw spring-boot:run
    ```

    The application starts on **port 8081** (or your configured port).

4.  **Database Configuration:**

      - JDBC URL: `jdbc:mysql://${DB_HOST:localhost}:3306/paymentdb`
      - Configure your credentials in `src/main/resources/application.yaml`.

## API Endpoints

Base path: `/payment`

### Payment Management API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/payment/doPayment` | Process a new payment for an order |
| GET | `/payment/getPaymentDetailsByOrder/{orderId}` | Get payment details by Order ID |

-----

### Request/Response Examples

  - **Do Payment:**

    ```http
    POST /payment/doPayment
    ```

    **Request Body:**

    ```json
    {
      "orderId": 12345,
      "amount": 5000,
      "referenceNumber": "REF-998877",
      "paymentmode": "CREDIT_CARD"
    }
    ```

    **Response:** `200 OK` with the generated Payment ID.

  - **Get Payment Details by Order ID:**

    ```http
    GET /payment/getPaymentDetailsByOrder/12345
    ```

    **Response:** `200 OK`

    ```json
    {
      "paymentId": 1,
      "status": "SUCCESS",
      "paymentMode": "CREDIT_CARD",
      "amount": 5000,
      "paymentDate": "2024-05-20T10:15:30Z",
      "orderId": 12345
    }
    ```

### Error Response Format

```json
{
  "errorMessage": "Payment details not found for the given Order ID",
  "errorCode": "PAYMENT_NOT_FOUND"
}
```

## Database Schema

### Entity: TransactionDetails (Table: `TRANSACTION_DETAILS`)

| Column | Type | Description |
|--------|------|-------------|
| `id` | Long | Primary Key (Auto-generated) |
| `ORDER_ID` | Long | Associated Order ID |
| `PAYMENT_MODE` | String | Mode (CASH, PAYPAL, etc.) |
| `REFERENCE_NUMBER`| String | External reference/transaction ID |
| `PAYMENT_DATE` | Instant | Timestamp of transaction |
| `PAYMENT_STATUS` | String | Status of the payment |
| `PAYMENT_AMOUNT` | Long | Amount paid |

### DTOs & Enums

  - **PaymentRequest** – Input DTO containing order details and payment mode.
  - **PaymentResponse** – Output DTO for displaying payment status and history.
  - **PaymentMode** – Enum supporting `CASH`, `PAYPAL`, `DEBIT_CARD`, `CREDIT_CARD`.
  - **ErrorResponse** – Standardized error structure.

## Design Patterns

1.  **N-Tier (Layered) Architecture** – Controller → Service → Repository.
2.  **Inversion of Control (IoC)** – Constructor-based dependency injection via `@RequiredArgsConstructor`.
3.  **Data Transfer Object (DTO) Pattern** – Separates internal `TransactionDetails` entity from external API models.
4.  **Builder Pattern** – Utilized for clean object instantiation in services.
5.  **Strategy Pattern** – Abstracted database interactions via Spring Data JPA.
6.  **Singleton Pattern** – Managed Spring Beans for service and controller layers.

## Future Enhancements

  - Integration with external Payment Gateways (Stripe/PayPal SDKs)
  - Implement JWT-based Security
  - Add Circuit Breaker patterns (Resilience4j) for inter-service communication
  - Enable transaction auditing and logging
  - Add unit tests using Mockito and JUnit 5

