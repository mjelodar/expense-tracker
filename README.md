``# 💰 ExpenseTracker

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