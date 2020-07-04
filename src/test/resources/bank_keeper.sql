ALTER DATABASE docker COLLATE 'utf8_general_ci';


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE bank_keeper;
TRUNCATE TABLE client;
TRUNCATE TABLE bank_keeper_clients;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO bank_keeper(from_crm, full_name, email, remote_id, phone, position, type_global, type_local, type_polish, create_date, avatar, country_code, mobile )
VALUES(1, 'Franek1', 'franky1@wp.pl', '111', '111222333', 'Striker', 0, 0, 1, '2018-02-14 10:30:00', '/avatar1.jpg', 'PL', '+48000111222');

INSERT INTO bank_keeper(from_crm, full_name, email, remote_id, phone, position, type_global, type_local, type_polish, create_date, avatar, country_code, mobile )
VALUES(1, 'Franek2', 'franky2@wp.pl', '222', '111222333', 'GoalKeeper', 0, 0, 1, '2018-02-14 10:30:00', '/avatar2.jpg', 'PL', '+48000111222');

INSERT INTO bank_keeper(from_crm, full_name, email, remote_id, phone, position, type_global, type_local, type_polish, create_date, avatar, country_code, mobile )
VALUES(1, 'Franek3', 'franky3@wp.pl', '333','111222333', 'Midfielder', 0, 0, 1, '2018-02-14 10:30:00', '/avatar3.jpg', 'PL', '+48000111222');

INSERT INTO bank_keeper(from_crm, full_name, email, remote_id, phone, position, type_global, type_local, type_polish, create_date, avatar, country_code, mobile )
VALUES(0, 'Krystyna4', 'christiano4@wp.pl','444', '111222333', 'Striker', 0, 0, 1, '2018-02-14 10:30:00', '/avatar4.jpg', 'PL', '+48000111222');

INSERT INTO bank_keeper(from_crm, full_name, email, remote_id, phone, position, type_global, type_local, type_polish, create_date, avatar, country_code, mobile )
VALUES(1, 'Krystyna5', 'christiano5@wp.pl', '555','111222333', 'Striker', 0, 1, 1, '2018-02-14 10:30:00', '/avatar4.jpg', 'DE', '+48000111222');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('ABC TEST', '110025', 'ABC TEST Company PL1', 'ABC TEST Company PL1', '1234', '09876');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('ABC TEST', '110025', 'ABC TEST Company PL2', 'ABC TEST Company PL2', '4321', '67890');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('XYZ TEST', '56982', 'XYZ TEST Company PL1', 'XYZ TEST Company PL1', '5566', '8899');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('XYZ TEST', '56982', 'XYZ TEST Company PL2', 'XYZ TEST Company PL2', '6655', '9988');


INSERT INTO bank_keeper_clients(bank_keeper_id, clients_id )
VALUES (1, 1);

INSERT INTO bank_keeper_clients(bank_keeper_id, clients_id )
VALUES (1, 2);

INSERT INTO bank_keeper_clients(bank_keeper_id, clients_id )
VALUES (1, 3);

INSERT INTO bank_keeper_clients(bank_keeper_id, clients_id )
VALUES (2, 3);

INSERT INTO bank_keeper_clients(bank_keeper_id, clients_id )
VALUES (2, 4);