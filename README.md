# API Documentation: Guitar Spring API

## Base URL

All API requests are made to the following base URL:

**`https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars`**

This URL will serve as the root endpoint for all API calls.

## Overview

This API allows users to perform CRUD operations on a collection of guitars. It supports basic operations like creating, reading, updating, and deleting guitars. 

The API is deployed to **AWS Elastic Beanstalk**, with a **AWS RDS MySQL database** for persistent data storage. 

### Why Docker Wasn't Used:
While Docker provides many benefits, such as containerization and environment consistency, it was not necessary for this project. Here are the reasons why deploying directly to AWS Elastic Beanstalk was a better choice:

1. **Simplicity**: 
   - AWS Elastic Beanstalk handles much of the infrastructure management, making deployment simpler and faster. It provides a platform-as-a-service (PaaS) environment, so we don't need to manage individual containers as we would in Docker.
  
2. **Cost Efficiency**: 
   - For this relatively simple API project, using AWS Elastic Beanstalk directly is more cost-effective as it abstracts away the complexity of managing Docker containers, networking, and storage resources.

3. **Managed Service**: 
   - AWS Elastic Beanstalk automatically handles scaling, monitoring, and security, reducing the need for additional management overhead, which would be required in Docker deployments.

4. **Easier Integration with AWS Services**: 
   - AWS Elastic Beanstalk provides seamless integration with AWS services like RDS (for database management) and S3 (for file storage). This makes it easier to manage, scale, and maintain the application without additional configuration.

5. **Less Overhead**: 
   - Docker introduces complexity in terms of container management and configuration. Since the application is a simple REST API with straightforward requirements, deploying directly to Elastic Beanstalk avoids unnecessary overhead while providing the needed environment and infrastructure.

In conclusion, while Docker is powerful for more complex projects or microservices architectures, Elastic Beanstalk was a more efficient and suitable choice for this application, especially considering ease of use, scalability, and cost.

## Authentication

No authentication is required to access the public endpoints of this API.

## Endpoints

### 1. **Get All Guitars**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars`
- **Method:** `GET`
- **Description:** Retrieves a list of all guitars in the system.
- **Request Example:**
  ```bash
  GET https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars
- **Response Example:**
  ```json
  [
    {
      "id": 1,
      "make": "Gibson",
      "model": "Explorer",
      "woodType": "Mahogany",
      "price": 1500.0
    },
    {
      "id": 2,
      "make": "Fender",
      "model": "Stratocaster",
      "woodType": "Alder",
      "price": 1200.0
    }
  ]
- **Response Code:**
  ```http
  200 OK
---
### 2. **Get Guitar by ID**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/{id}`
- **Method:** `GET`
- **Description:** Retrieves a specific guitar by its ID.
- **Request Example:**
  ```bash
  GET https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/1
- **Response Example:**
  ```json
  {
    "id": 1,
    "make": "Gibson",
    "model": "Explorer",
    "woodType": "Mahogany",
    "price": 1500.0
  }
- **Response Code:**
  ```http
  200 OK
- **Error Response:**
  - If guitar not found:
    ```http
    404 Not Found
---
### 3. **Get Guitars by Make**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/make/{make}`
- **Method:** `GET`
- **Description:** Retrieves all guitars of a specific make.
- **Request Example:**
  ```bash
  GET https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/make/Gibson
- **Response Example:**
  ```json
  [
    {
      "id": 1,
      "make": "Gibson",
      "model": "Explorer",
      "woodType": "Mahogany",
      "price": 1500.0
    },
    {
      "id": 7,
      "make": "Gibson",
      "model": "Explorer",
      "woodType": "Mahogany",
      "price": 1799.0
    },
    {
      "id": 14,
      "make": "Gibson",
      "model": "SG Standard",
      "woodType": "Mahogany",
      "price": 1599.99
    }
  ]
- **Response Code:**
  ```http
  200 OK
- **Error Response:**
  - If no guitars found:
    ```http
    404 Not Found
---
### 4. **Create Guitar**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars`
- **Method:** `POST`
- **Description:** Creates a new guitar in the system.
- **Request Example:**
  ```bash
  POST https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars
Payload:
```json
{
  "make": "Gibson",
  "model": "Les Paul",
  "woodType": "Mahogany",
  "price": 2000.0
}
```
  - **Response Example:**
    ```json
    {
      "id": 1,
      "make": "Gibson",
      "model": "Les Paul",
      "woodType": "Mahogany",
      "price": 2000.0
    }
  - **Response Code:**
    ```http
    201 Created
  - **Error Response:**
    - If validation fails:
      ```http
      400 Bad Request
---
### 5. **Bulk Create Guitars**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/bulk-guitars`
- **Method:** `POST`
- **Description:** Creates multiple guitars in bulk.
- **Request Example:**
  ```bash
  POST https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/bulk-guitars
Payload:
```json
[
  {
    "make": "Gibson",
    "model": "Les Paul",
    "woodType": "Mahogany",
    "price": 1500.0
  },
  {
    "make": "Fender",
    "model": "Stratocaster",
    "woodType": "Alder",
    "price": 1200.0
  }
]
```
  - **Response Example:**
    ```json
    "Guitars created successfully!"
  - **Response Code:**
    ```http
    201 Created
  - **Error Response:**
    - If validation fails:
      ```http
      400 Bad Request
---
### 6. **Update Guitar**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/{id}`
- **Method:** `PUT`
- **Description:** Updates the details of an existing guitar.
- **Request Example:**
  ```bash
  PUT https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/1
Payload:
```json
{
  "make": "Gibson",
  "model": "Les Paul Custom",
  "woodType": "Mahogany",
  "price": 2200.0
}
```
  - **Response Example:**
    ```json
    {
      "id": 1,
      "make": "Gibson",
      "model": "Les Paul Custom",
      "woodType": "Mahogany",
      "price": 2200.0
    }
  - **Response Code:**
    ```http
    200 OK
  - **Error Response:**
    - If guitar not found:
      ```http
      404 Not Found
    - If validation fails
      ```http
      400 Bad Request
---
### 7. **Delete Guitar**

- **URL:** `https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a specific guitar by its ID.
- **Request Example:**
  ```bash
  DELETE https://Guitar-spring-api-project-env.eba-eku8jsny.us-east-1.elasticbeanstalk.com/api/guitars/1
- **Response Example:** No body content.
- **Response Code:**
  ```http
  204 No Content
- **Error Response:**
  - If guitar not found:
    ```http
    404 Not Found
---
## Error Handling

The API follows standard HTTP error codes for different error cases:
- **400 Bad Request:** Invalid or incomplete data in the request body.
- **404 Not Found:** The requested resource (e.g., guitar) was not found.
- **500 Internal Server Error:** Unexpected server errors.

## Validation Errors

In the event of a validation error, the response body will include a message describing which fields are invalid.

For example:
```json
{
  "model": "Model is required",
  "price": "Price must be a positive value"
}
```
---
## Notes

- All responses are returned in JSON format.
- Ensure that the guitar data is validated before sending requests (e.g., required fields such as `make`, `model`, `woodType`, and `price`).
- You can send multiple guitars in the bulk creation endpoint.

    
