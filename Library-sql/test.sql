create table if not exists user (
	uid int primary key,
    username varchar(10) not null,
    password varchar(32) not null
);
alter table user modify id int unsigned;
alter table user modify username varchar(20);
drop table if exists user;
