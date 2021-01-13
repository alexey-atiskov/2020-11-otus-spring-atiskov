DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS comments_info;

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
    id_genre BIGINT,
    FOREIGN KEY(id_genre) REFERENCES genres(gen_id)
);

CREATE TABLE comments (
    comment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    value VARCHAR(255)
);

CREATE TABLE comments_info (
    id_comment BIGINT,
    FOREIGN KEY(id_comment) REFERENCES comments(comment_id),
    id_book BIGINT,
    FOREIGN KEY(id_book) REFERENCES books(book_id)
);

CREATE TABLE book_authors (
    id_comment BIGINT,
    FOREIGN KEY(auth_id) REFERENCES authors(auth_id),
    id_book BIGINT,
    FOREIGN KEY(id_book) REFERENCES books(book_id)
);
