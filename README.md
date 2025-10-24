#  💰 ExpenseTracker

**ExpenseTracker** is a personal finance management application built with Spring Boot, designed to help users record, categorize, and analyze their spending habits.
With support for custom categories, reporting rules, and notifications, ExpenseTracker empowers users to take control of their expenses efficiently.
---
### 🧩 Features

* 📊 **Expense Tracking** — Record daily spending with amount, date, and description.

* 🏷️ **Categories & Subcategories** — Organize expenses under flexible categories (e.g. Food → Restaurant, Transport → Taxi).

* ⚙️ **Custom Report Rules** — Define rules such as:

`   “Notify me when I spend more than $100 on Restaurants this month.”
`
* 🔔 **Smart Notifications** — Get alerts when spending exceeds your defined limits.

* 📈 **Expense Reports** — View summaries and insights on your spending patterns.

* 🧑‍💻 **User Authentication** — Each user must manage their own expenses securely.

* 🧾 **RESTful API** — Easily integrate with frontend apps or other services.

---

### 🏗️ Tech Stack
| Layer                       | Technology                  |
|:----------------------------|:----------------------------|
| **Backend**                 | Spring Boot (Java 21)       |
| **Database**                | MySQL / Redis               |
| **Build Tool**              | Maven                       |
| **Containerization**        | Docker                      |
| **ORM**                     | Spring Data JPA / Hibernate |
| **Security**                | Spring Security (JWT)       |
| **Notifications**           | In-App notification         |

---
### 🚀 Getting Started
**Prerequisites**

Make sure you have installed:
* Java 21
* Maven 3.9+
* Docker
---

### 🧱 Build & Run Locally

```Clone the repository
git clone https://github.com/your-username/expense-tracker.git
cd expense-tracker

Build the application
docker build -t expense-tracker .

Run the application
docker run -d -p 8080:8080 --name expense-tracker expense-tracker
```

---
### 🗂️ Project Structure

```
expense-tracker/
├── src
│     └── main
│           ├── java
│           │     └── com
│           │         └── snapp
│           │             └── expense_tracker
│           │                 ├── auth
│           │                 │     ├── controller   # REST controllers
│           │                 │     ├── domin        # Entities
│           │                 │     ├── exception    # Business Exceptions
│           │                 │     ├── model        # Views, DTOs
│           │                 │     ├── props        # Application properties
│           │                 │     ├── repository   # JPA repositories
│           │                 │     ├── service      # Business logic
│           │                 │     └── util         # Utilities (JwtUtil, RedisUtil, ...)
│           │                 ├── common
│           │                 │     ├── config       # Application Configuration (Security config, ..)
│           │                 │     ├── enums        # Shared enums
│           │                 │     ├── event        # Shared events
│           │                 │     ├── exception    # Global exception handling
│           │                 │     ├── model        # Shared DTOs
│           │                 │     └── util         # Shared Utilities
│           │                 ├── cost
│           │                 │     ├── controller
│           │                 │     ├── domain
│           │                 │     ├── model
│           │                 │     │     └── mapper
│           │                 │     ├── repository
│           │                 │     └── service
│           │                 ├── lookup
│           │                 │     ├── controller
│           │                 │     ├── domain
│           │                 │     ├── model
│           │                 │     ├── repository
│           │                 │     └── service
│           │                 ├── notification
│           │                 │     ├── controller
│           │                 │     ├── domain
│           │                 │     ├── eventHandler
│           │                 │     ├── exception
│           │                 │     ├── model
│           │                 │     │     └── mapper
│           │                 │     ├── repository
│           │                 │     └── service
│           │                 └── report
│           │                     ├── controller
│           │                     ├── domain
│           │                     ├── eventHandler
│           │                     ├── exception
│           │                     ├── model
│           │                     │     └── mapper
│           │                     ├── repository
│           │                     ├── scheduler
│           │                     └── service
│           └── resources
│               └── db
│                   └── migration
```

---

### 📘 Example Usage
**Create a user**
```
Post /api/auth/signup
{
  "firstName": "foo",
  "surname": "bar",
  "username": "foobar",
  "password": "12345678"
}
```

**Login**
```
Post /api/auth/login
{
  "username": "f.bar",
  "password": "12345678"
}
```

**Get category**
```
Get api/lookup/category/search?text=foo
```

**Get subcategory**
```
Get api/lookup/subcategory/search/{categoryId}?text=foo
```

**Add an expense**
```
Post /api/cost/expense
{
  "categoryId": 2,
  "categoryName": "Food",
  "subCategoryId": 7,
  "subCategoryName": "Restaurants",
  "amount": 44
}
```

**Define a rule**
```
Post /api/report/rule
{
  "description": "Maybe walking have a good effect in you health",
  "categoryId": 3,
  "categoryName": "Transportation",
  "subcategoryId": 10,
  "subcategoryName": "Taxi",
  "timeUnit": "WEEK",
  "timePeriod": 1,
  "operator": "GREATER_THAN",
  "threshold": 50.24
}
```

**Get In-app notification**
```
GET /api/notification
```
