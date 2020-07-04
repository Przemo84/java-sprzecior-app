ALTER DATABASE docker COLLATE 'utf8_general_ci';


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE travel_alert;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO travel_alert(content, country_code, lang, level, title)
VALUES ('Dummmy test Holland content', 'NL','PL', 'SAFE','Holland test title' );

INSERT INTO travel_alert(content, country_code, lang, level, title)
VALUES ('Dummmy test Germany content', 'DE','PL', 'SAFE','Germany test title' );

INSERT INTO travel_alert(content, country_code, lang, level, title)
VALUES ('Dummmy test English content', 'DE','EN', 'SAFE','English test title' );