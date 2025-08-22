# JournalApp 📝  
A **Spring Boot backend** application that provides a secure and scalable **REST API** for managing journal entries.  

This project demonstrates production-grade backend development practices including **MongoDB Atlas**, **Redis caching**, **Spring Security with JWT**, **custom HTTP status handling**, **logging**, and **external API integration**.

---

## 🚀 Features
- ✅ **10+ REST API Endpoints** for CRUD operations on journal entries  
- ✅ **MongoDB Atlas** for persistent, cloud-hosted data storage  
- ✅ **Redis** caching layer for faster data retrieval  
- ✅ **Spring Security + JWT** authentication & authorization  
- ✅ **Custom HTTP status codes** for clear error/success responses  
- ✅ **Lombok** to reduce boilerplate code  
- ✅ **Integrated external Weather API** to enrich journal entries with live weather data  
- ✅ **Centralized logging** for monitoring and debugging  

---

## 🛠 Tech Stack
- **Java 17**  
- **Spring Boot 3**  
- **Spring Web / REST**  
- **Spring Data MongoDB (MongoDB Atlas)**  
- **Spring Security + JWT**  
- **Spring Cache (Redis)**  
- **Project Lombok**  
- **Maven**  
- **Weather API** (OpenWeather or similar)  

---
## 📌 API Endpoints

The JournalApp provides secure REST APIs grouped into **User**, **Journal**, **Public**, and **Admin** categories.

### 👤 User
| Method | Endpoint        | Description              | Auth Required |
|--------|-----------------|--------------------------|---------------|
| POST   | `/public/login` | Returns a greeting message | ❌            |

---

### 📒 Journal
| Method | Endpoint             | Description                        | Auth Required |
|--------|----------------------|------------------------------------|---------------|
| GET    | `/journal`           | Get all journal entries            | ✅ (User)     |
| POST   | `/journal`           | Add a new journal entry            | ✅ (User)     |
| GET    | `/journal/{id}`      | Get journal entry by ID            | ✅ (User)     |
| PUT    | `/journal/{id}`      | Update journal entry by ID         | ✅ (User)     |
| DELETE | `/journal/{id}`      | Delete journal entry by ID         | ✅ (User)     |

---

### 🌍 Public
| Method | Endpoint          | Description                   | Auth Required |
|--------|-------------------|-------------------------------|---------------|
| GET    | `/public/health`  | Health check for the service  | ❌            |
| POST   | `/public/signup`  | Register a new user           | ❌            |
| POST   | `/public/login`   | Login and get JWT token       | ❌            |

---

### 🛠 Admin
| Method | Endpoint            | Description                     | Auth Required |
|--------|---------------------|---------------------------------|---------------|
| GET    | `/admin/all-user`   | Fetch all registered users      | ✅ (Admin)    |
| GET    | `/admin/clear-cache`| Clear Redis cache               | ✅ (Admin)    |
| POST   | `/admin/create-admin-user`|Create a new admin user    | ✅ (Admin)    |

---

🔐 **Authentication**:  
- Endpoints marked with **✅** require a valid **JWT token** in the `Authorization: Bearer <token>` header.  
- `public` endpoints can be accessed without authentication.  
- `admin` endpoints require **admin privileges**.

---

## 🔐 Authentication & Security
- **JWT-based authentication**  
- Secure endpoints with **role-based authorization**  
- Passwords stored using **BCrypt hashing**  

---

## ⚡ Caching
- Frequently accessed journal entries cached in **Redis**  
- Automatic cache eviction and refresh strategies  

---

## 🗂 Project Structure
