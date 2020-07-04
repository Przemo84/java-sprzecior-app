ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE  news;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO news (archived, published, title, image, content, create_date, deleted, lang, maker_id)
VALUES('0', '1', 'Title1', '/image1.png', 'Some test content1', '2018-08-15 10:30:00', 0,'PL', 4);

INSERT INTO news (archived, published, title, image, content, create_date, deleted, lang, maker_id)
VALUES('0', '1', 'Title2', '/image2.png', 'Some test content2', '2018-08-15 10:30:00',0, 'PL', 4);

INSERT INTO news (archived, published, title, image, content, create_date, deleted, lang, maker_id)
VALUES('0', '1', 'Title3', '/image3.png', 'Some test content3', '2018-08-15 10:30:00',0, 'DE', 4);

INSERT INTO news (archived, published, title, image, content, create_date, deleted, lang, maker_id)
VALUES('1', '1', 'Title4-archived', '/image4.png', 'Some test content4', '2018-08-15 10:30:00',0, 'PL', 4);

INSERT INTO news (archived, published, title, image, content, create_date, deleted, lang, maker_id)
VALUES('0', '1', 'Title5-deleted', '/image4.png', 'Some test content5', '2018-08-15 10:30:00', 1, 'PL', 4);
