ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE bank_offer;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO bank_offer(deleted, checker_id, lang, name, maker_id, image, link, create_date)
VALUES(0, 1, 'PL', 'BankOffer1', 2, 'img1.png', 'http://www.test-strona1.com', '2018-08-14 10:30:00');

INSERT INTO bank_offer(deleted, checker_id, lang, name, maker_id, image, link, create_date)
VALUES(0, 1, 'PL', 'BankOffer2', 2, 'img2.png', 'http://www.test-strona2.com', '2018-08-14 10:30:00');

INSERT INTO bank_offer(deleted, checker_id, lang, name, maker_id, image, link, create_date)
VALUES(0, 1, 'PL', 'BankOffer3', 2, 'img3.png', 'http://www.test-strona3.com', '2018-08-14 10:30:00');

INSERT INTO bank_offer(deleted, checker_id, lang, name, maker_id, image, link, create_date)
VALUES(0, 1, 'DE', 'BankOffer4', 2, 'img4.png', 'http://www.test-strona4.com', '2018-08-14 10:30:00');

INSERT INTO bank_offer(deleted, checker_id, lang, name, maker_id, image, link, create_date)
VALUES(1, 1, 'PL', 'BankOffer5', 2, 'img5.png', 'http://www.test-strona5.com', '2018-08-14 10:30:00');