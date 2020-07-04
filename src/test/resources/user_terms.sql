ALTER DATABASE docker COLLATE 'utf8_general_ci';


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE user_terms;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user_terms(user_id, confirm_date, terms)
VALUES (1, '2018-08-14 11:30:00', 'Test terms');

INSERT INTO user_terms(user_id, confirm_date, terms)
VALUES (2, '2018-08-14 11:30:00', 'Test terms');

