insert into authors (`firstname`, `lastname`, `secondaryname`) values ('alexey', 'atiskov', 'jurievich');
insert into authors (`firstname`, `lastname`, `secondaryname`) values ('putin', 'vladimir', 'vladimirovich');

insert into genres (`name`) values ('horror');
insert into genres (`name`) values ('humor');

insert into books (`name`, `id_genre`) values ('book1', 2);
insert into books (`name`, `id_genre`) values ('book2', 1);

insert into comments (`value`) values ('comment_1');
insert into comments (`value`) values ('comment_2');
insert into comments (`value`) values ('comment_3');

insert into comments_info (`id_comment`, `id_book`) values (1, 1);
insert into comments_info (`id_comment`, `id_book`) values (2, 1);
insert into comments_info (`id_comment`, `id_book`) values (3, 2);

insert into book_authors (`auth_id`, `id_book`) values (1, 1);
insert into book_authors (`auth_id`, `id_book`) values (2, 2);
