ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE embassy;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO embassy (address, api_id, country_code, email, fax, lat, lng, name, phone, site_address, active)
VALUES ('Bratysława', '1', 'SK', 'bratyslawa_slowacja@com.pl', '222333444', 50.00, 16.13, 'Test ambasada w bratysławie', '123456', 'www', 1);

INSERT INTO embassy (address, api_id, country_code, email, fax, lat, lng, name, phone, site_address, active)
VALUES ('kijów', '2', 'UA', 'kijow_ukraina@com.pl', '222333111', 13.12, 14.17, 'Test ambasada w kijowie', '123456', 'www', 1);

INSERT INTO embassy (address, api_id, country_code, email, fax, lat, lng, name, phone, site_address, active)
VALUES ('berlin', '3', 'DE', 'berlin_deutschland@com.pl', '222333111', 51.12, 13.17, 'Test ambasada w Belinie', '123456', 'www', 1);

INSERT INTO embassy (address, api_id, country_code, email, fax, lat, lng, name, phone, site_address, active)
VALUES ('Wien', '4', 'AT', 'wieden_austria@com.pl', '222333444', 50.00, 15.00, 'Test ambasada we Wiedniu', '123456', 'www', 0);

