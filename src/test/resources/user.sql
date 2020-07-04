ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE user;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user (active, create_date, email, first_name, last_name, otp_attempts, one_time_password, one_time_password_confirmed, password, phone_number, role_id, username)
VALUES (1, '2018-01-21 18:57:14', 'test1@user.com', 'test1_user', 'test_user', 0, '123456', 0, 'password', '333444555', 1, 'test1_user');

INSERT INTO user (active, create_date, email, first_name, last_name, otp_attempts, one_time_password, one_time_password_confirmed, password, phone_number, role_id, username)
VALUES (1, '2018-01-21 18:57:14', 'test2@user.com', 'test2_user', 'test_user', 0, '123456', 0, 'password', '333444555', 1, 'test2_user');

INSERT INTO user (active, create_date, email, first_name, last_name, otp_attempts, one_time_password, one_time_password_confirmed, password, phone_number, role_id, username)
VALUES (1, '2018-01-21 18:57:14', 'test3@user.com', 'test3_user', 'test_user', 0, '123456', 0, 'password', '333444555', 1, 'test3_user');

INSERT INTO user (active, create_date, email, first_name, last_name, otp_attempts, one_time_password, one_time_password_confirmed, password, phone_number, role_id, username)
VALUES (0, '2018-01-21 18:57:14', 'test4@user.com', 'test4_user', 'test_user', 0, '123456', 0, 'password', '333444555', 1, 'test4_user');

INSERT INTO user (active, create_date, email, first_name, last_name, otp_attempts, one_time_password, one_time_password_confirmed, password, phone_number, role_id, username)
VALUES (1, '2018-01-21 18:57:14', 'test5@user.com', 'test5_user', 'test_user', 0, '123456', 0, 'password', '333444555', 1, 'test5_user');