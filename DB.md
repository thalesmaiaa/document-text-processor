### 1. User

| Column     | Type      | Constraints      |
|------------|-----------|------------------|
| id         | UUID (PK) | PRIMARY KEY      |
| email      | VARCHAR   | UNIQUE, NOT NULL |
| name       | VARCHAR   |                  |
| created_at | TIMESTAMP | DEFAULT now()    |

---

### 2. Document

| Column        | Type      | Constraints                     |
|---------------|-----------|---------------------------------|
| id            | UUID (PK) | PRIMARY KEY                     |
| user_id       | UUID      | FOREIGN KEY REFERENCES User(id) |
| type          | VARCHAR   | NOT NULL ('pdf', 'image', etc)  |
| s3_location   | VARCHAR   | NOT NULL                        |
| upload_time   | TIMESTAMP | DEFAULT now()                   |
| status        | VARCHAR   | NOT NULL                        |
| original_name | VARCHAR   |                                 |

---

### 3. ProcessingHistory

| Column       | Type        | Constraints                                  |
|--------------|-------------|----------------------------------------------|
| id           | SERIAL (PK) | PRIMARY KEY                                  |
| document_id  | UUID        | FOREIGN KEY REFERENCES Document(id)          |
| step         | VARCHAR     | NOT NULL ('uploaded', 'text_extracted', etc) |
| status       | VARCHAR     | NOT NULL ('success', 'failed')               |
| message      | TEXT        |                                              |
| processed_at | TIMESTAMP   | DEFAULT now()                                |

---

### 4. ExtractedText

| Column      | Type        | Constraints                         |
|-------------|-------------|-------------------------------------|
| id          | SERIAL (PK) | PRIMARY KEY                         |
| document_id | UUID        | FOREIGN KEY REFERENCES Document(id) |
| text_data   | TEXT        |                                     |
| created_at  | TIMESTAMP   | DEFAULT now()                       |

---

### 6. SentimentAnalysis

| Column      | Type        | Constraints                         |
|-------------|-------------|-------------------------------------|
| id          | SERIAL (PK) | PRIMARY KEY                         |
| document_id | UUID        | FOREIGN KEY REFERENCES Document(id) |
| sentiment   | VARCHAR     | ('POSITIVE', 'NEGATIVE', etc)       |
| scores      | JSONB       | (e.g., {"Positive": 0.95, ...})     |
| created_at  | TIMESTAMP   | DEFAULT now()                       |

---

## Diagram (Mermaid)

```mermaid
erDiagram
    USER {
        UUID id PK
        VARCHAR email
        VARCHAR name
        TIMESTAMP created_at
    }
    DOCUMENT {
        UUID id PK
        UUID user_id FK
        VARCHAR type
        VARCHAR s3_location
        TIMESTAMP upload_time
        VARCHAR status
        VARCHAR original_name
    }
    PROCESSING_HISTORY {
        SERIAL id PK
        UUID document_id FK
        VARCHAR step
        VARCHAR status
        TEXT message
        TIMESTAMP processed_at
    }
    EXTRACTED_TEXT {
        SERIAL id PK
        UUID document_id FK
        TEXT text_data
        TIMESTAMP created_at
    }

    SENTIMENT_ANALYSIS {
        SERIAL id PK
        UUID document_id FK
        VARCHAR sentiment
        JSONB scores
        TIMESTAMP created_at
    }

    USER ||--o{ DOCUMENT: "has"
    DOCUMENT ||--o{ PROCESSING_HISTORY: "has"
    DOCUMENT ||--o{ EXTRACTED_TEXT: "may have"
    DOCUMENT ||--o{ SENTIMENT_ANALYSIS: "may have"
```