# Event Manager

## Project Overview

Event Manager is a backend application built with **Spring Boot** that provides APIs to manage events and places, with support for:

- PostgreSQL for relational data
- MongoDB for document-based data
- Google OAuth2 for authentication
- JWT for stateless authorization
- Docker and Docker Compose for local development
- Swagger/OpenAPI for API documentation
- **ViaCEP integration** for address lookup and enrichment based on Brazilian CEP

The application is designed to be used by a front-end client that handles the OAuth redirect flow and stores the issued JWT token.

---

## Prerequisites

To run this project, you will need:

- **Java 21**
- **Maven 3.9+** (or use the included `mvnw` wrapper)
- **Docker** and **Docker Compose** (optional, but recommended)
- A **Google OAuth 2.0 Client ID and Secret** (for authentication flow)

---

## Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/felipesalmazodev/eventmanager-back.git
cd eventmanager-back
```

### 2. Setting up Google OAuth 2.0 Client ID and Secret

- Go to [Google Cloud Console](https://console.cloud.google.com)
- Select an existing project or create one
- Go to API & Services > OAuth consent screen > create a consent screen and set the **Audience** to External
- With the consent screen created, go to APIs and Services > Credentials > Create credentials > Oauth Client ID
- Configure as a Web Application and in the Authorized redirect URIs, paste: `http://localhost:8080/login/oauth2/code/google`
- Create and copy the generated Client ID and Client Secret


### 3. Environment variables

This project uses environment variables for configuration. There is a file called:

```bash
.env.example
```

Copy it to `.env`:

```bash
cp .env.example .env
```

Then edit `.env` and fill in the required values, especially:

- `GOOGLE_OAUTH_CLIENT_ID`
- `GOOGLE_OAUTH_CLIENT_SECRET`
- `JWT_SECRET` (must be at least 32 characters for HS256)

Example (simplified):

```env
POSTGRES_URL=jdbc:postgresql://postgres:5432/eventmanager
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

MONGODB_URI=mongodb://mongo:27017/eventmanager

GOOGLE_OAUTH_CLIENT_ID=your-google-client-id
GOOGLE_OAUTH_CLIENT_SECRET=your-google-client-secret

JWT_SECRET=change-me-to-a-very-long-random-secret-32chars-min
OAUTH2_SUCCESS_REDIRECT=http://localhost:3000/auth/callback
CORS_ALLOWED_ORIGINS=http://localhost:3000
```

---

## Running the Application

You can run the application in **two main ways**: via IDE/Maven or via Docker Compose.

### Option 1: Run via IDE (or Maven)

#### 1. Start the databases

You can start only the databases using Docker Compose profiles:

```bash
docker compose --profile db up -d
```

This will start:
- PostgreSQL on `localhost:5432`
- MongoDB on `localhost:27017`

#### 2. Run the application

From the project root:

```bash
./mvnw spring-boot:run
```

Or run the main class directly from your IDE (IntelliJ, VS Code, etc).

The backend will start at:

```
http://localhost:8080
```

---

### Option 2: Run everything with Docker Compose

This will start:
- PostgreSQL
- MongoDB
- The Spring Boot application

Make sure your `.env` file exists and you have changed the URLs of the databases from localhost to the name of the container (is commented on the `.env.example`), then run:

```bash
docker compose --profile full up --build
```

The API will be available at:

```
http://localhost:8080
```

---

### Docker Compose Profiles

This project uses Docker Compose profiles:

- **db**: starts only PostgreSQL and MongoDB
  ```bash
  docker compose --profile db up -d
  ```

- **full**: starts PostgreSQL, MongoDB, and the backend application
  ```bash
  docker compose --profile full up --build
  ```

---

## API Documentation (Swagger)

After starting the application, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

This page contains:
- All available endpoints
- Request/response models
- The ability to test the API directly from the browser

---

## ViaCEP Integration

This project integrates with the **ViaCEP** public API to retrieve and validate address information based on a Brazilian CEP (postal code).  
The integration is used to enrich place-related data and to ensure that CEP information is valid and consistent.

No additional setup is required for ViaCEP, since it is a public API. The application will automatically call it when needed.

---

## Testing Instructions

You can test the application manually using **Swagger UI**, **Postman**, or any HTTP client (e.g., curl or Insomnia).

### 1. Using Swagger UI

1. Start the application.
2. Open the Swagger UI in your browser:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```
3. Browse the available endpoints and try them directly from the UI.
4. For protected endpoints:
  - First perform the OAuth login flow from the front-end (or your configured client). If you prefer, call the http://localhost:8080/oauth2/authorization/google and log in with your google account. The browser will redirect you to the front-end. Copy the value of the param "token".
  - If you logged from the front-end, obtain the JWT token returned by the backend in the localstorage.
  - Click **Authorize** in Swagger and provide:
    ```
    Authorization: Bearer <your-jwt-token>
    ```
5. Execute requests and verify responses, status codes, and payloads.

### 2. Using Postman (or similar tools)

1. Create a new request to the desired endpoint (e.g., create place, list events, etc.).
2. If the endpoint is protected, add the header:
   ```
   Authorization: Bearer <your-jwt-token>
   ```
3. Send the request and validate:
  - HTTP status code
  - Response body
  - Error messages (for validation and business rules)

### 3. Testing the ViaCEP Integration

- Create or update a Place using a valid Brazilian CEP.
- The application should automatically call ViaCEP to validate/enrich the address data.
- Try an invalid CEP to verify that the API returns a proper validation or integration error.
- These are some valid CEPs:
  -  **01001000** --- Praça da Sé, São Paulo - SP
  - **20040002** --- Centro, Rio de Janeiro - RJ
  -  **30140071** --- Belo Horizonte - MG
  -  **70040900** --- Brasília - DF
  -  **80010000** --- Curitiba - PR

### 4. Typical Test Scenarios

- Create, update, list, and delete Places.
- Create Events with valid and invalid date ranges.
- Try to create an Event for a Place that is already booked in the same time range.
- Access protected endpoints without a token and verify that the API returns **401 Unauthorized**.
- Access protected endpoints with a valid token and verify that the request succeeds.

---

## Known Issues

- Google OAuth requires correct configuration of:
  - Redirect URI in Google Cloud Console
  - `GOOGLE_OAUTH_CLIENT_ID` and `GOOGLE_OAUTH_CLIENT_SECRET` in `.env`
- If the application fails to start due to database connection errors, ensure:
  - Docker containers are running
  - Ports `5432` (Postgres) and `27017` (MongoDB) are not already in use
- The `JWT_SECRET` must be at least 32 characters long for HS256, otherwise Spring Security may fail to start.
- On first startup with Docker, the application may take a few seconds longer due to database container initialization.
- The ViaCEP service is an external dependency; temporary outages or network issues may affect address lookup features.
