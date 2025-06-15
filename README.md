# InterviewPrep Backend API

📚 A Spring Boot-powered backend to manage DSA questions, personal notes & future real-time discussion threads — built for serious interview prep!

---

## 🚀 Tech Stack

- **Java 22**
- **Spring Boot 3.x**
- **MySQL**
- **Lombok**
- **JWT-based Authentication**
- **Maven**

---

## ✨ Features

- 🔐 User Registration & JWT Authentication
- 📌 Add / Update / Delete DSA Questions
- 🧠 Add Notes to Each Question
- 🔍 Filter Questions by Topic & Solved Status
- 🗃️ Get Questions by Logged-in User
- 📅 Auto-stamps creation date
- 🧱 **Follows SOLID Principles** for scalable, maintainable architecture
- 💬 **Upcoming:** Real-time discussion threads per question (WebSocket-based)

---

## 📦 Project Structure

```
com.zoro.interviewprep
├── auth
│   ├── AuthController.java
│   ├── AuthResponse.java
│   ├── AuthService.java
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthFilter.java
│   ├── JwtUtil.java
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   └── SecurityConfig.java
│
├── config
│   └── CorsConfig.java
│
├── note
│   ├── Note.java
│   ├── NoteController.java
│   ├── NoteRepository.java
│   ├── NoteService.java
│   └── dto
│       ├── NoteRequestDTO.java
│       └── NoteResponseDTO.java
│
├── question
│   ├── Question.java
│   ├── QuestionController.java
│   ├── QuestionRepository.java
│   ├── QuestionService.java
│   └── dto
│       ├── QuestionRequestDTO.java
│       └── QuestionResponseDTO.java
│
├── user
│   ├── Role.java
│   ├── User.java
│   ├── UserController.java
│   ├── UserRepository.java
│   ├── UserService.java
│   ├── UserUtil.java
│   └── dto
│       ├── UserRequestDTO.java
│       └── UserResponseDTO.java
│
└── InterviewPrepApplication.java

resources/
├── static/
├── templates/
└── application.properties
```

---

## 📌 Sample API Endpoints

### 🔐 Auth

- `POST /api/auth/register`
- `POST /api/auth/login`

### 📌 Question APIs

- `POST /api/questions/add`
- `PUT /api/questions/update/{id}`
- `GET /api/questions/user?email=...`
- `GET /api/questions/filter/topic?topic=...`
- `GET /api/questions/filter/solved?solved=true`
- `DELETE /api/questions/{id}`

### 📝 Notes APIs

- `POST /api/notes/add`
- `GET /api/notes/question/{questionId}`
- `PUT /api/notes/update/{noteId}`
- `DELETE /api/notes/{noteId}`

---

## 🛠️ Getting Started

```bash
# 1. Clone the repo
https://github.com/yuji090/interview-prep.git

# 2. Open in your IDE & setup MySQL DB
# 3. Add DB credentials in `application.properties`

# 4. Run
./mvnw spring-boot:run
```

---

## 💡 Future Plans

- 💬 Add Leetcode-like discussion threads (WebSocket)
- 🧠 Per-question live chatroom
- 📊 Track user solve streak & performance stats
- 🌐 Build React Frontend to consume APIs

---

## 🤝 Contributions

Pull requests & issues are welcome!

---

## 📜 License

MIT License

