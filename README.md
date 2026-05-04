## Модель данных

### Таблицы

* **patients**
* **doctors**
* **appointments**

### Связи

* Один пациент → много записей
* Один врач → много записей

---

## API

### API Service

#### Создание записи

```
POST /appointments
```

#### Отчёты

```
GET /appointments/search
GET /appointments/reports/count-day
GET /appointments/reports/top-doctors
GET /appointments/reports/top-patients
```

---

### Data Service

```
GET /appointments/search
GET /reports/count-day
GET /reports/top-doctors
GET /reports/top-patients
```

---

## Запуск проекта

```
docker-compose up --build
```

---

## Пример запроса

### Создание записи

```json
POST /appointments

{
  "patientName": "Ivan Ivanov",
  "doctorName": "Dr. Smith",
  "doctorSpecialization": "Therapist",
  "date": "2026-05-01T10:00:00"
}
```
