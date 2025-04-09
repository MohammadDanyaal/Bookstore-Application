# 📚 Bookstore Application

A secure and scalable Spring Boot RESTful API that allows users to sign up, login with JWT authentication, and perform full CRUD operations on books. Includes advanced features like searching, filtering, pagination, and Swagger UI integration.

---

## ✅ Features

- User Signup and Login with JWT
- Secure Role-Based Authentication
- CRUD for Books
- Filter by Category, Author, Rating, Price Range
- Partial Title Search
- Pagination Support
- Swagger UI API Documentation
- Global Exception Handling
- DTO Layer Implementation

---

## 🛠 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/bookstore-app.git
   cd bookstore-app
   ```

2. **Database Setup (MySQL)**
   Create a database named `bookstore` and update credentials in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Build & Run the Project**
   ```bash
   mvn spring-boot:run
   ```

---

## 🔐 Authentication

### ➔ Signup (No Token Required)
```http
POST /api/auth/signup
```
**Body:**
```json
{
  "email": "user@gmail.com",
  "password": "123456"
}
```

### ➔ Login (Get JWT Token)
```http
POST /api/auth/login
```
**Body:**
```json
{
  "email": "user@gmail.com",
  "password": "123456"
}
```
**Response:**
```json
{
  "token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

> Use this token in the `Authorization` header for protected routes.

---

## 📆 Book API Endpoints

### ➔ Get All Books
```http
GET /api/books
Authorization: Bearer <token>
```

### ➔ Get Book by ID
```http
GET /api/books/{id}
Authorization: Bearer <token>
```

### ➔ Create a Book
```http
POST /api/books
Authorization: Bearer <token>
```
**Body:**
```json
{
  "title": "Deep Work",
  "author": "Cal Newport",
  "price": 499.99,
  "category": "Productivity",
  "rating": 4.7
}
```

### ➔ Update a Book
```http
PUT /api/books/{id}
Authorization: Bearer <token>
```

### ➔ Delete a Book
```http
DELETE /api/books/{id}
Authorization: Bearer <token>
```

---

## 🔍 Search & Filter API

```http
GET /api/books/search
Authorization: Bearer <token>
```

### ✅ Supported Query Params:

| Param       | Description                      |
|-------------|----------------------------------|
| title       | Partial match on book title      |
| author      | Filter by author name            |
| category    | Filter by category               |
| rating      | Minimum rating filter            |
| minPrice    | Minimum price filter             |
| maxPrice    | Maximum price filter             |
| page        | Page number (default 0)          |
| size        | Page size (default 5)            |

**Example:**
```
GET /api/books/search?title=Deep&rating=4&minPrice=200&maxPrice=600&page=0&size=5
```

---

## 📁 Swagger Authorization Steps

1. Open: `http://localhost:8080/swagger-ui/index.html`
2. Click on "Authorize 🔒" (top-right)
3. Paste:
   ```
   Bearer <your_token_here>
   ```
4. Click **Authorize**

---

## 📸 Screenshots (Add your images here)

| # | Screenshot Description             | Image |
|--:|------------------------------------|-------|
| 1 | Signup Screen                      | ![Signup](images/signup.png) |
| 2 | Login & Token                      | ![Login](images/login.png) |
| 3 | Swagger Auth with JWT              | ![Swagger Auth](images/swagger-auth.png) |
| 4 | Swagger Book Endpoints             | ![Swagger Endpoints](images/swagger-endpoints.png) |
| 5 | Create Book                        | ![Create Book](images/create-book.png) |
| 6 | Get All Books                      | ![All Books](images/all-books.png) |
| 7 | Get Book by ID                     | ![Get Book](images/get-book.png) |
| 8 | Update Book                        | ![Update](images/update.png) |
| 9 | Delete Book                        | ![Delete](images/delete.png) |
|10 | Search Filters                     | ![Filters](images/filters.png) |
|11 | Pagination in Action               | ![Pagination](images/pagination.png) |
|12 | Category Filter Result             | ![Category](images/category.png) |
|13 | Author Filter Result               | ![Author](images/author.png) |
|14 | Swagger Response Example           | ![Swagger Response](images/swagger-response.png) |
|15 | Final Demo Snapshot                | ![Demo](images/demo.png) |

> 📂 Put all images inside a folder `images/` at project root

---

## 🧠 Assumptions & Enhancements

- All users get default `ROLE_USER`
- JWT tokens are valid for 24 hours
- Token is only validated, not role-checked deeply
- Currently uses inbuilt `UserDetailsService`
- Category and Author filters are partial match-based
- Swagger doesn't support token refresh (can be added)
- Pagination is handled via Spring Data's Pageable

---

## ✅ Project Completed ✅

| Feature                | Status |
|------------------------|--------|
| Signup/Login (JWT)     | ✅     |
| Book CRUD              | ✅     |
| Search & Filtering     | ✅     |
| Pagination             | ✅     |
| Swagger UI             | ✅     |
| Secure Routes (JWT)    | ✅     |
| DTO Layer              | ✅     |
| Final README           | ✅     |

---

**Made with 💻 using Spring Boot 3, Java 17, MySQL, JWT, Swagger**

