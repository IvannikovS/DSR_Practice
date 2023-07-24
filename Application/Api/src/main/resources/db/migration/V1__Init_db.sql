CREATE TABLE link
(
    id SERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    short_code TEXT NOT NULL UNIQUE
);

CREATE INDEX idx_link_url ON link (url);