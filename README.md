# DataQuest 🎮

**DataQuest** is a website for publishing gaming industry news, reviews of popular releases, and events. The project is a classic web application (monolith) that provides a news feed, gaming event pages, and administration tools.

> **Note:** This project is a legacy solution built using classic Java servlets (Jakarta EE) and JSP, without the use of modern frameworks like Spring. The project demonstrates a good understanding of the basic principles of HTTP, sessions, and low-level interaction with the database and S3.

## 🛠 Technology Stack

### Core Technologies and Environment
* **Java**: `17`
* **Servlet Container**: `Apache Tomcat 10.1.x`
* **Project Build**: Maven

### Backend
* **Core**: Jakarta Servlet API 6.1.0
* **Database**: PostgreSQL (driver `org.postgresql:postgresql:42.7.8`)
* **Connection Pool**: HikariCP 5.0.1
* **Password encryption**: Bcrypt (`at.favre.lib:bcrypt:0.10.2`)
* **Media File Storage**: S3-compatible storage - Yandex Cloud (via AWS SDK `software.amazon.awssdk:s3:2.20.94`)
* **Logging**: SLF4J + Logback
**Utilities**: Lombok

### Frontend
* JSTL 2.0.0 (Jakarta Server Pages Standard Tag Library) + JSP
* HTML5 / CSS3 / JavaScript (Vanilla JS)
* Custom fonts (Gilroy)

## 📌 Main functionality

Based on the architecture, the project implements:
* **News publishing**: creating and reading articles about events and new games.
* **Authentication/* **Authorization**: secure password storage using Bcrypt hashing.
* **Cloud image storage**: images for articles, banners, and covers are uploaded and served via the Yandex Cloud Object Storage bucket.
* **Optimized database handling**: use of the HikariCP connection pool for efficient resource utilization.
