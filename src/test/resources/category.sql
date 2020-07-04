ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE category;
TRUNCATE TABLE news;
SET FOREIGN_KEY_CHECKS = 1;

-- DELETE FROM category;

INSERT INTO category (name, lang, create_date) VALUES('category', 'EN', '2018-02-03 09:23:54');