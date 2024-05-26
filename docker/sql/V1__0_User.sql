CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    openid TEXT NOT NULL UNIQUE ,
    nickname TEXT NOT NULL ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX users_id ON users (id)