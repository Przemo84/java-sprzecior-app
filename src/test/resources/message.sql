ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE message;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(0 ,'/image1.png','0','Title1','2018-08-14 10:30:00', 'Some test content1', 'DE', 'MESSAGE');

INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(0 ,'/image2.png','0','Title2','2018-08-14 10:30:00', 'Some test content2', 'PL', 'MESSAGE');

INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(0 ,'/image3.png','0','Title3','2018-08-14 10:30:00', 'Some test content3', 'PL', 'MESSAGE');

INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(0 ,'/image4.png','0','Title4','2018-08-14 10:30:00', 'Some test content4', 'PL', 'EVENT');

INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(0 ,'/image5.png','0','Title5','2018-08-14 10:30:00', 'Some test content5', 'PL', 'EVENT');

INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(1 ,'/image6.png','0','Title6-deleted','2018-08-14 10:30:00', 'Some test content6', 'PL', 'MESSAGE');

INSERT INTO message (deleted, image, archived, title, create_date, content, lang, type)
VALUES(0 ,'/image7.png','1','Title6-archived','2018-08-14 10:30:00', 'Some test content7', 'PL', 'MESSAGE');