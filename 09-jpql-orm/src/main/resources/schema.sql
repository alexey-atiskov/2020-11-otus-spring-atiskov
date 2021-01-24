DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS comments_info;
DROP TABLE IF EXISTS book_authors;

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

CREATE TABLE comments_info (
    comment_id bigint,
    FOREIGN KEY(comment_id) REFERENCES comments(comment_id),
    book_id bigint,
    FOREIGN KEY(book_id) REFERENCES books(book_id) on delete cascade
);

CREATE TABLE book_authors (
    auth_id bigint,
    FOREIGN KEY(auth_id) REFERENCES authors(auth_id),
    book_id bigint,
    FOREIGN KEY(book_id) REFERENCES books(book_id)
);
