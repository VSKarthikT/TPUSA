# Travel Portfolio USA (TPUSA)

## Overview

WRITE SOME THING about project

---

## Functionalities

### 1. **Authentication and Authorization**

- **User Registration:**
  - Endpoint: `POST /api/auth/register`
  - Allows users to register with their email, password, and name.
  - Passwords are securely hashed using `BCryptPasswordEncoder` before storage.
- **User Login:**
  - Endpoint: `POST /api/auth/login`
  - Authenticates users with email and password.
  - Returns a JWT token upon successful login.
- **JWT-Based Security:**
  - Secures API endpoints using JWT tokens.
  - Roles (`ROLE_ADMIN`, `ROLE_USER`) are embedded in the token for access control.

---

### 2. **Role-Based Access Control**

- **Public Endpoints (No Authentication Required):**
  - `/api/auth/register` - User registration.
  - `/api/auth/login` - User login.
- **Admin-Only Endpoints (`ROLE_ADMIN`):**
  - `/api/admin/all_users`: Fetches a list of all registered users.
- **User-Specific Endpoints (`ROLE_USER`):**
  - `/api/user/updateBio`: Allows authenticated users to update their bio.

---

### 3. **JWT Implementation**

- **Token Generation:**
  - Tokens include user-specific claims like `email` and `role`.
  - Valid for 1 hour.
- **Token Validation:**
  - Verifies token integrity and expiration.
  - Extracts user information from the token for authorization.
- **Secure Filter:**
  - A custom `JWTFilter` ensures that each request contains a valid token.

---

### 4. **Endpoints Overview**

| Endpoint               | HTTP Method | Role Required | Description                               |
| ---------------------- | ----------- | ------------- | ----------------------------------------- |
| `/api/auth/register`   | POST        | Public        | Register a new user.                      |
| `/api/auth/login`      | POST        | Public        | Login and receive a JWT.                  |
| `/api/admin/all_users` | GET         | ROLE_ADMIN    | Fetch all registered users (Admin only).  |
| `/api/user/updateBio`  | POST        | ROLE_USER     | Update bio of the authenticated user.     |
| `/default`             | GET         | Authenticated | Sample endpoint returning "Hello World!". |

---

### 5. **Database Design**

To connect the database, update the `application.properties` file:

- **User Table:**
  - Fields: `id`, `email`, `passwordHash`, `name`, `role`, `bio`, `createdAt`, `updatedAt`.
  - Passwords are securely hashed.
- **Repositories:**
  - `UserRepository`: Interface for user-related database operations.

Database
I have used Supabase to store our data. Here are the details:

| Parameter | Value                                 |
| --------- | ------------------------------------- |
| Host      | `db.xyoksdwpzcyhswjgydzx.supabase.co` |
| Port      | `5432`                                |
| Database  | `postgres`                            |
| User      | `postgres`                            |
| Password  | `duxcy9-buwxag-wichaZ`                |

---

### 6. **Security Configuration**

- **JWTFilter:**
  - Validates incoming requests by parsing and verifying the JWT.
- **Security Filter Chain:**
  - Configures endpoints with access control based on user roles.
- **Session Management:**
  - Stateless session policy to ensure secure and scalable interaction.

---

### 7. **How to Run**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd TPUSA
   ```
