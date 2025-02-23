CREATE TABLE "user" (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(50) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL
);