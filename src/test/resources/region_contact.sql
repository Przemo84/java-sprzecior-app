ALTER DATABASE docker COLLATE 'utf8_general_ci';


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE region_contact;
TRUNCATE TABLE region_contact_info;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO region_contact_info(country, country_code, region, region_type)
VALUES ('Holandia', 'NL', 'Europe, Middle East & Africa', 'EMEA' );

INSERT INTO region_contact_info(country, country_code, region, region_type)
VALUES ('Belgia', 'BE', 'Europe, Middle East & Africa', 'EMEA' );


INSERT INTO region_contact(address, contact, checked, name, region_contact_info_id)
VALUES ('adres1 w Holandii', '+48 111111111', 1, 'Test Citi BankHouse 1', 1 );

INSERT INTO region_contact(address, contact, checked, name, region_contact_info_id)
VALUES ('adres2 w Holandii', '+48 222222222', 1, 'Test Citi BankHouse 2', 1 );

INSERT INTO region_contact(address, contact, checked, name, region_contact_info_id)
VALUES ('adres1 w Belgii', '+48 333333333', 1, 'Test Citi BankHouse 1', 2 );

INSERT INTO region_contact(address, contact, checked, name, region_contact_info_id)
VALUES ('adres2 w Belgii', '+48 44444444', 1, 'Test Citi BankHouse 2', 2 );

