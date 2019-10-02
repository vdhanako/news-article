insert into author (author_id, author_name) values (1, 'venkat');
insert into author (author_id, author_name) values (2, 'robert');

insert into article (article_id, header, short_description, text, publish_date, author_id) values (1, 'some header', 'some description', 'some text', '2019-10-01', 1);
insert into article (article_id, header, short_description, text, publish_date, author_id) values (2, 'some header', 'some description', 'some text', '2019-10-01', 2);
insert into article (article_id, header, short_description, text, publish_date, author_id) values (3, 'some header', 'some description', 'some text', '2019-10-02', 2);

insert into article_keywords (article_article_id, keywords) values (1, 'some');
insert into article_keywords (article_article_id, keywords) values (2, 'some');