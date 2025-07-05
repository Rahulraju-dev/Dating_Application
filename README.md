#  MatchSphere – Match Recommendation System (Backend)

**MatchSphere** is a backend project built with **Java Spring Boot**, designed to recommend users based on age and shared interests.  
This is my **first backend project**, focused on building and testing clean REST APIs.

---

## 🔧 Features

- User registration & login
- Get users by ID, name, email, age, phone
- Filter by gender (male/female)
- Search users by name/email
- Recommend top N best matches based on:
  - Opposite gender
  - Shared interests
  - Age difference

---

## 🛠 Tech Stack

- Java + Spring Boot
- Spring Data JPA + MySQL
- Maven + Lombok
- Postman (API testing)

---

## 📬 Sample API Endpoints

| Method | Endpoint                             | Description               |
|--------|--------------------------------------|---------------------------|
| POST   | `/users`                             | Register a user           |
| POST   | `/api/users/login`                   | Login with name/password  |
| GET    | `/users/best-match/{id}/{top}`       | Top N match recommendations |
| GET    | `/users/gender/male`                 | Get all male users        |
| GET    | `/users/search/name/{letters}`       | Search users by name      |

---

## 🚀 Getting Started

1. Clone repo & open in Eclipse or VS Code  
2. Set up MySQL DB in `application.properties`  
3. Run the app (`Dating_Recomendation_EngineApplication`)  
4. Test APIs using Postman

---

## 📌 Status

✅ Backend Complete  
🔜 Frontend in progress (HTML, CSS, JS)

---

## 🙋‍♂️ Author

**Rahul Raju**  
B.Tech CSE | Backend & Web Development  
[GitHub](https://github.com/Rahulraju-dev) | [LinkedIn](https://linkedin.com/in/rahulraju-dev)

