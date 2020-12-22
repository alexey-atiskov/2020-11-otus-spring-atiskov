insert into authors (id, `firstname`, `lastname`, `secondaryname`) values (1, 'alexey', 'atiskov', 'jurievich');
insert into authors (id, `firstname`, `lastname`, `secondaryname`) values (2, 'putin', 'vladimir', 'vladimirovich');
insert into genres (id, `name`) values (1, 'horror');
insert into genres (id, `name`) values (2, 'humor');
insert into books (id, `name`, `id_author`, `id_genre`) values (1, 'book1', 1, 2);
insert into books (id, `name`, `id_author`, `id_genre`) values (2, 'book2', 2, 1);
insert into books (id, `name`, `id_author`, `id_genre`) values (4, 'book4', 1, 1);
