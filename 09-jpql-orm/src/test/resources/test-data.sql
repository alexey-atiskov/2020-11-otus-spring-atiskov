insert into authors (`firstname`, `lastname`, `secondaryname`) values ('alexey', 'atiskov', 'jurievich');
insert into authors (`firstname`, `lastname`, `secondaryname`) values ('putin', 'vladimir', 'vladimirovich');
insert into genres (`name`) values ('horror');
insert into genres (`name`) values ('humor');
insert into books (`name`, `id_author`, `id_genre`) values ('book1', 1, 2);
insert into books (`name`, `id_author`, `id_genre`) values ('book2', 2, 1);
insert into books (`name`, `id_author`, `id_genre`) values ('book4', 1, 1);
