CREATE TABLE link
(
    id SERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    short_code TEXT NOT NULL UNIQUE
);

CREATE INDEX idx_link_url ON link (url);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE token (
    id SERIAL PRIMARY KEY,
    expired BOOLEAN NOT NULL,
    revoked BOOLEAN NOT NULL,
    token VARCHAR(255),
    token_type VARCHAR(255),
    user_id INTEGER REFERENCES users(id)
);