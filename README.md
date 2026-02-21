# Habit Tracker Backend API

Backend-приложение для трекинга привычек, реализованное на Kotlin с использованием Spring Boot. Предоставляет REST API для управления пользователями, привычками и отметками об их выполнении.

## 🚀 Запуск проекта

### Предварительные требования
- Java 21 или выше
- Gradle 8+ (используется оболочка из проекта)
- Любая среда разработки (IntelliJ IDEA, Eclipse) или командная строка

### Шаги запуска
1. Клонируйте репозиторий:
   ```bash
   git clone <repository-url>
   cd habit-tracker
   ```

2. Запустите приложение одним из способов:
   - Через командную строку:
     ```bash
     ./gradlew bootRun
     ```
   - Через IDE: запустите класс `Application.kt` (если отсутствует, то точкой входа является Spring Boot).

3. Приложение будет доступно по адресу: `http://localhost:8080`

4. Консоль базы данных H2 доступна по адресу: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:habit_tracker_db`
   - Имя пользователя: `alfoll`
   - Пароль: (пустой)

## 🔐 Аутентификация и регистрация

### Регистрация нового пользователя
**Эндпоинт:** `POST /api/auth/register`

**Тело запроса (JSON):**
```json
{
  "name": "Your name",
  "email": "user@example.com",
  "password": "your_password (min size = 8)"
}
```

**Ответ:**
```json
{
  "accessToken": "your_jwt_token"
}
```

### Вход в систему
**Эндпоинт:** `POST /api/auth/login`

**Тело запроса (JSON):**
```json
{
  "email": "user@example.com",
  "password": "your_password"
}
```

**Ответ:** Аналогично регистрации – возвращает JWT токен.

### Использование токена
Полученный токен необходимо передавать в заголовке запроса для доступа к защищенным эндпоинтам:
```
Authorization: Bearer <your_token>
```

📸 Примеры запросов (Postman)

1. **/api/ping**
<img width="845" height="420" alt="image" src="https://github.com/user-attachments/assets/2d8d6247-d5c5-4d34-94c2-2fea6cdfc552" />

2. **Регистрация пользователя**
<img width="858" height="448" alt="image" src="https://github.com/user-attachments/assets/634b9a42-b61b-4eac-9a0b-3aab22278378" />

3. **Вход пользователя**
<img width="867" height="453" alt="image" src="https://github.com/user-attachments/assets/e5ffc87e-bca6-4feb-b423-7b26d1834b0b" />

4. **Создание привычки**
<img width="853" height="479" alt="image" src="https://github.com/user-attachments/assets/9233eb0e-129d-46c9-97c4-9c697eae8076" />

5. **Получение привычек юзера**
<img width="855" height="548" alt="image" src="https://github.com/user-attachments/assets/c840d2ef-7d24-40e6-8f1a-fdf68dda4cc5" />

6. **Получение привычки юзера по id привычки**
<img width="863" height="473" alt="image" src="https://github.com/user-attachments/assets/3baa657f-fc2e-4a04-aca7-d3adf3005ed6" />

7. **Изменение привычки (только название) по id привычки**
<img width="853" height="442" alt="image" src="https://github.com/user-attachments/assets/d14b1578-f6e8-49af-ad8f-814a5f675b2c" />

8. **Удаление привычки**
<img width="848" height="392" alt="image" src="https://github.com/user-attachments/assets/99a04c33-2a51-4796-ace9-59b8b04c01ce" />

9. **Установление лога на привычку**
<img width="853" height="432" alt="image" src="https://github.com/user-attachments/assets/edc2c2c3-08f8-4998-af5e-7a2b4dcd3a1a" />

10. **Получение всех логов привычки по id привычки**
<img width="853" height="470" alt="image" src="https://github.com/user-attachments/assets/a6c293f3-fe4b-493e-9ce6-b12943cbdc44" />

11. **Получение всех логов привычки за период**
<img width="852" height="613" alt="image" src="https://github.com/user-attachments/assets/2a493710-69f6-4234-bd08-e708e0e9d5e9" />


## 🗃️ Сущности и связи

### Пользователь (User)
- `id` – уникальный идентификатор
- `name` – имя пользователя
- `email` – электронная почта (уникальная)
- `passwordHash` – хэш пароля

### Привычка (Habit)
- `id` – уникальный идентификатор
- `title` – название привычки
- `createdAt` – дата создания (автоматически)
- `user` – владелец (связь Many-to-One с User)

### Лог выполнения (HabitLog)
- `id` – уникальный идентификатор
- `date` – дата выполнения
- `done` – выполнена ли привычка
- `habit` – связанная привычка (связь Many-to-One с Habit)

### Связи
- Один **пользователь** может иметь много **привычек** (`User` → `Habit`: One-to-Many)
- Одна **привычка** может иметь много **логов** выполнения (`Habit` → `HabitLog`: One-to-Many)
- Все связи реализованы через JPA/Hibernate с аннотациями `@ManyToOne`

## 📊 База данных
- Используется **H2** (в памяти) для разработки
- Автоматическое создание таблиц через Hibernate (`ddl-auto: update`)
- Для просмотра данных используйте H2 Console: `http://localhost:8080/h2-console`

## 🛠️ Технологии
- **Kotlin 2.2.21**
- **Spring Boot 4.0.1** (исправьте на 3.x, так как версия 4.x не существует; вероятно, опечатка)
- **Spring Security** + **JWT** (jjwt 0.12.3)
- **Spring Data JPA** + **Hibernate**
- **H2 Database**
