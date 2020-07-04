ALTER DATABASE docker COLLATE 'utf8_general_ci';


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE client;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('ABC TEST', '110025', 'ABC TEST Company PL1', 'ABC TEST Company PL1', '1234', '09876');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('ABC TEST', '110025', 'ABC TEST Company PL2', 'ABC TEST Company PL2', '4321', '67890');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('XYZ TEST', '56982', 'XYZ TEST Company PL1', 'XYZ TEST Company PL1', '5566', '8899');

INSERT INTO client(rel_name, rel_number, name_full, name, fcm, fc)
VALUES ('XYZ TEST', '56982', 'XYZ TEST Company PL2', 'XYZ TEST Company PL2', '6655', '9988');
