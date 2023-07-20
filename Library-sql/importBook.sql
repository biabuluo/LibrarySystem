use library;

insert into category (cid, cname, icon) values (1, '文学', 'el-icon-reading');
insert into category (cid, cname, icon) values (2, '流行', 'el-icon-headset');
insert into category (cid, cname, icon) values (3, '学习', 'el-icon-collection');
insert into category (cid, cname, icon) values (4, '生活', 'el-icon-food');
insert into category (cid, cname, icon) values (5, '经管', 'el-icon-document-copy');
insert into category (cid, cname, icon) values (6, '科技', 'el-icon-cpu');


insert into book (ISBN, title, author, publishDate, publisher, abs, cid, location, nums) values ('123', '老人与海', '海明威', '2022-11-15', '暨南大学出版社', '这是一本书', 1, '11-0-10', 10);
insert into book (ISBN , title, author, publishDate, publisher, abs, cid, location, nums) values ('456', '三体', '刘慈欣', '2022-11-11', '暨南大学出版社', '科幻小说', 1, '10-1-2', 10);
insert into book (ISBN, title, author, publishDate, publisher, abs, cid, location, nums) values ('789', '高等数学', '张三', '2022-11-11', '暨南大学出版社', '大学教材', 3, '19-10-2', 30);
