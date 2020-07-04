ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS=0;
DELETE FROM fcm_token;

-- INSERT INTO user (id, active, create_date, email, first_name, last_name, must_change_password, password, phone_number, username)
-- VALUES (1, 1, '2018-01-21 18:57:14', 'test12312312312@user.com', 'test_user', 'test_user', 0, 'password', '333444555', 'test_user')
-- ON DUPLICATE KEY UPDATE id = id+1;

INSERT INTO fcm_token (token, type, user_id) VALUES ('ios.fcm.toke', 'IOS', 1);
SET FOREIGN_KEY_CHECKS=1;