DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS comments_info;
DROP TABLE IF EXISTS book_authors;

CREATE TABLE authors (
    auth_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    secondaryname VARCHAR(255)
);

CREATE TABLE genres (
    gen_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books(
    book_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    gen_id BIGINT,
    FOREIGN KEY(gen_id) REFERENCES genres(gen_id)
);

CREATE TABLE comments (
    comment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    value VARCHAR(255)
);

CREATE TABLE comments_info (
    comment_id BIGINT,
    FOREIGN KEY(comment_id) REFERENCES comments(comment_id),
    book_id BIGINT,
    FOREIGN KEY(book_id) REFERENCES books(book_id)
);

CREATE TABLE book_authors (
    auth_id BIGINT,
    FOREIGN KEY(auth_id) REFERENCES authors(auth_id),
    book_id BIGINT,
    FOREIGN KEY(book_id) REFERENCES books(book_id)
);
