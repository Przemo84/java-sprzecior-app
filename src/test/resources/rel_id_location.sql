ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE rel_id_location;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO rel_id_location (country_code, rel_id, rel_name )
VALUES ('DE', '1001', 'DEF');

INSERT INTO rel_id_location (country_code, rel_id, rel_name )
VALUES ('PL', '1002', 'PLN');

INSERT INTO rel_id_location (country_code, rel_id, rel_name )
VALUES ('AT', '1003', 'ABC');

INSERT INTO rel_id_location (country_code, rel_id, rel_name )
VALUES ('GB', '1004', 'GBR');
