CREATE TABLE user_table (
  id SERIAL PRIMARY KEY,
  user_name VARCHAR(50) NOT NULL,
  user_email_id VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(60) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);
