# Travel Portfolio USA (TPUSA)
#Swagger UI 
http://localhost:8080/swagger-ui/index.html#/

### Overview

Travel Portfolio USA (TPUSA) is a secure, user-friendly application designed for travel enthusiasts to manage and showcase their travel experiences. The platform supports robust authentication and authorization features, ensuring the safety and privacy of user data. With a role-based access control system and seamless JWT integration, TPUSA enables administrators and users to interact efficiently within a secure ecosystem.

---

### Functionalities

#### 1. **Authentication and Authorization**

- **User Registration:**

  - **Endpoint:** `POST /api/auth/register`
  - Allows users to register with their email, password, and name.
  - Passwords are securely hashed using `BCryptPasswordEncoder` before storage.

- **User Login:**

  - **Endpoint:** `POST /api/auth/login`
  - Authenticates users using email and password.
  - Returns a JWT token upon successful login.

- **JWT-Based Security:**

  - Secures API endpoints using JWT tokens.
  - Embeds roles (e.g., `ROLE_ADMIN`, `ROLE_USER`) within the token for access control.

- **Login Process:**
  1. **Login Request:**
     - User sends their email and password to the `/api/auth/login` endpoint.
  2. **AuthService:**
     - Calls `AuthenticationManager.authenticate()` with a `UsernamePasswordAuthenticationToken`.
  3. **AuthenticationManager:**
     - Delegates authentication to `DaoAuthenticationProvider`.
  4. **DaoAuthenticationProvider:**
     - Extracts email and password from the token.
     - Calls `MyUserDetailsService.loadUserByUsername()` to fetch user details.
  5. **Database Interaction:**
     - The `MyUserDetailsService` queries the database using `UserRepository.findByEmail()`.
     - Retrieves the user record and maps it to a `User` entity.
  6. **Validation:**
     - The user entity is wrapped in a `UserPrincipal` object and validated against the provided password using `BCryptPasswordEncoder`.
  7. **Token Creation:**
     - If valid, an authenticated `UsernamePasswordAuthenticationToken` is created and stored in the `SecurityContext`.

---

#### 2. **Role-Based Access Control**

- **Public Endpoints (No Authentication Required):**

  - `/api/auth/register` - User registration.
  - `/api/auth/login` - User login.

- **Admin-Only Endpoints (ROLE_ADMIN):**

  - `/api/admin/all_users` - Fetches a list of all registered users.

- **User-Specific Endpoints (ROLE_USER):**
  - `/api/user/updateBio` - Allows authenticated users to update their bio.

---

#### 3. **JWT Implementation**

- **Token Generation:**

  - Tokens include user-specific claims like email and role.
  - Tokens have a validity of 1 hour.

- **Token Validation:**

  - Verifies token integrity and checks expiration.
  - Extracts user information from the token for authorization.

- **Secure Filter:**
  - A custom `JWTFilter` ensures that all incoming requests contain a valid token.
  - Tokens generated in a previous application session become invalid after the application restarts (this can be updated to use a static secret key for persistent validation).

---

#### 4. **Endpoints Overview**

| Endpoint               | HTTP Method | Role Required | Description                               |
| ---------------------- | ----------- | ------------- | ----------------------------------------- |
| `/api/auth/register`   | POST        | Public        | Register a new user.                      |
| `/api/auth/login`      | POST        | Public        | Login and receive a JWT.                  |
| `/api/admin/all_users` | GET         | ROLE_ADMIN    | Fetch all registered users (Admin only).  |
| `/api/user/updateBio`  | POST        | ROLE_USER     | Update the bio of the authenticated user. |
| `/default`             | GET         | Authenticated | Sample endpoint returning "Hello World!". |

---

#### 5. **Database Design**

To connect the database, update the `application.properties` file with the following details:

- **User Table:**

  - **Fields:** `id`, `email`, `passwordHash`, `name`, `role`, `bio`, `createdAt`, `updatedAt`.
  - Passwords are securely hashed before storage.

- **Repositories:**

  - `UserRepository`: An interface for user-related database operations.

- **Database Host Details:**
  - **Host:** `db.xyoksdwpzcyhswjgydzx.supabase.co`
  - **Port:** `5432`
  - **Database:** `postgres`
  - **User:** `postgres`
  - **Password:** `duxcy9-buwxag-wichaZ`

---

#### 6. **Security Configuration**

- **JWTFilter:**

  - Validates incoming requests by parsing and verifying the JWT.

- **Security Filter Chain:**

  - Configures access control for endpoints based on user roles.

- **Session Management:**
  - Implements a stateless session policy to ensure secure and scalable interactions.

---

#### 7. **How to Run**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd TPUSA
   ```
