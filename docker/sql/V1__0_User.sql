CREATE TABLE unauthorized_user
(
    name      TEXT PRIMARY KEY NOT NULL,
    auth_code CHAR(16)         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX unauthorized_user_name ON unauthorized_user (name);

CREATE TABLE authorized_user
(
    internal_id SERIAL PRIMARY KEY NOT NULL,
    public_id   TEXT UNIQUE,
    discord_id  TEXT UNIQUE,
    password TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX authorized_user_id ON authorized_user (internal_id);

CREATE TABLE register_temp
(
    id CHAR(16) PRIMARY KEY NOT NULL ,
    name TEXT NOT NULL UNIQUE ,
    discord_id BIGINT UNIQUE ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
)
