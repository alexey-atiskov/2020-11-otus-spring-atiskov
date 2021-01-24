insert into authors (`firstname`, `lastname`, `secondaryname`) values ('alexey', 'atiskov', 'jurievich');
insert into authors (`firstname`, `lastname`, `secondaryname`) values ('putin', 'vladimir', 'vladimirovich');

insert into genres (`name`) values ('horror');
insert into genres (`name`) values ('humor');

insert into books (`name`, `gen_id`) values ('book1', 2);
insert into books (`name`, `gen_id`) values ('book2', 1);

insert into comments (`value`) values ('comment_1');
insert into comments (`value`) values ('comment_2');
insert into comments (`value`) values ('comment_3');

insert into comments_info (`comment_id`, `book_id`) values (1, 1);
insert into comments_info (`comment_id`, `book_id`) values (2, 1);
insert into comments_info (`comment_id`, `book_id`) values (3, 2);

insert into book_authors (`auth_id`, `book_id`) values (1, 1);
insert into book_authors (`auth_id`, `book_id`) values (2, 2);
