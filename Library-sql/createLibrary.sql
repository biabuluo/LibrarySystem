drop database library;

create database library;

use library;

create table user (
	uid int primary key auto_increment ,
    username varchar(10) not null unique,
    password varchar(32) not null default 'e10adc3949ba59abbe56e057f20f883e',
	name varchar(10) not null,
    nick varchar(10) not null unique,
    img varchar(255) default 'http://inews.gtimg.com/newsapp_match/0/15077040964/0',
    motto varchar(10) default '我爱读书',  
    phone varchar(11) not null unique, 
    isAdmin tinyint default false
    
)ENGINE=InnoDB DEFAULT character set = gbk;

create table category (
	cid int primary key AUTO_INCREMENT,
    cname varchar(10) not null,
    icon varchar(255) default 'el-icon-loading'
)ENGINE=InnoDB DEFAULT character set = gbk;

create table book (
	bid int primary key AUTO_INCREMENT,  
    ISBN varchar(13) unique not null,
    title varchar(20) not null,
    author varchar(20) not null,
    publishDate date not null,
    publisher varchar(20) not null,
    cover varchar(255) default 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
    abs varchar(255),
    cid int references category(cid),
    location varchar(20) not null ,
    nums int default 0
)ENGINE=InnoDB DEFAULT character set = gbk;

alter table book add constraint fk_bk_ct foreign key (cid) references category (cid) on update set null  on delete set null;

create table borrow (
	bid int references book(bid),
    uid varchar(10) references user(uid),
    borrowDate date not null,
    primary key (bid, uid)
)ENGINE=InnoDB DEFAULT character set = gbk;

create table comment (
	uid int references user.uid,
    ISBN int references book.ISBN,
    review varchar(300),
    commdate date not null,
    level float(2,1)
);

create view borrow_info as 
select borrowDate , user.uid as uid, book.bid as bid, name, username, title, ISBN, to_days(now())-to_days(borrowdate) as days_used 
from user, borrow, book
where borrow.uid = user.uid && borrow.bid = book.bid;

create view user_info as 
select user.*, count(borrow.uid) as borrownums 
from  user left join borrow on borrow.uid = user.uid
group by user.uid;

create view comment_info as  
select nick, img, title, review, commdate, level
from user, book, comment
where comment.uid = user.uid && comment.ISBN = book.ISBN;

create view category_info as
select c.cid,c.cname,count(b.bid) as nums
from category c left join book b on c.cid=b.cid
group by c.cid;

create view user_comment_info as 
select nick, title, review, username, commdate 
from user, comment, book
where comment.uid = user.uid && comment.ISBN = book.ISBN; 