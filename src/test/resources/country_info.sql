ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE country_info;
SET FOREIGN_KEY_CHECKS = 1;


DELETE FROM country_info;


INSERT INTO country_info(area, capital, capital_pl, country_code, flag, pkb_year, pkb, population)
VALUES('312000', 'Warsaw', 'Warszawa','PL', 'https://pl.wikipedia.org/wiki/Flaga_Polski#/media/File:Flag_of_Poland_(normative).svg','2000',300000000000,  '38000000');

INSERT INTO country_info(area, capital, capital_pl, country_code, flag, pkb_year, pkb, population)
VALUES('642000', 'Berlin', 'Berlin','DE','https://pl.wikipedia.org/wiki/Flaga_Niemiec#/media/File:Flag_of_Germany.svg', '2000' ,800000000000, '82000000');

INSERT INTO country_info(area, capital, capital_pl, country_code, flag, pkb_year, pkb, population)
VALUES('20000', 'Amsterdam', 'Amsterdam','NL', 'https://pl.wikipedia.org/wiki/Holandia#/media/File:Flag_of_the_Netherlands.svg', '2000', 20000000000, '1900000');

INSERT INTO country_info(area, capital, capital_pl, country_code, flag, pkb_year, pkb, population)
VALUES('19000', 'Brussels', 'Bruksela','BE', 'https://upload.wikimedia.org/wikipedia/commons/6/65/Flag_of_Belgium.svg', '2000', 1000000000, '1700000');


