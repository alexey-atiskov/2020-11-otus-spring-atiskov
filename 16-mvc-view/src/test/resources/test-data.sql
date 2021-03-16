CREATE TABLE authors (
    auth_id bigserial,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    secondaryname VARCHAR(255),
    PRIMARY KEY(auth_id)
);

CREATE TABLE genres (
    gen_id bigserial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books(
    book_id bigserial PRIMARY KEY,
    name VARCHAR(255),
    gen_id BIGINT,
    FOREIGN KEY(gen_id) REFERENCES genres(gen_id)
);

CREATE TABLE comments (
    comment_id bigserial PRIMARY KEY,
    value VARCHAR(255)
);