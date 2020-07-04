ALTER DATABASE docker COLLATE 'utf8_general_ci';

DELETE FROM emergency_number;

INSERT INTO emergency_number(phone_number, address, create_date)
VALUES('000111222', 'Warszawa1', '2018-02-15 12:00:00');
