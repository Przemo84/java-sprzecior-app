SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE role;
TRUNCATE TABLE role_modules;
TRUNCATE TABLE role_modules_unchecked;
TRUNCATE TABLE module;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO role (name, maker_id, checker_id) VALUES ('ROLE_ADMIN', 4, 5);
INSERT INTO role (name, maker_id, checker_id) VALUES ('ROLE_USER', 4, 5);
INSERT INTO role (name, maker_id, checker_id) VALUES ('ROLE_OTHER', 4, 5);

INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Wiadomości', "mod_messages");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Aktualności', "mod_news");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Ofert Bankowych', "mod_offers");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Placówek Dyplomatycznych', "mod_embassies");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Informacje o CITI', "mod_contacts");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Opiekunów Bankowych', "mod_keepers");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Lokalizacji relacji', "mod_rel_id_location");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Użytkowników', "mod_mobile_users");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Ustawień', "mod_settings");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Historii czynności', "mod_history");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Dostępów', "mod_permissions");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Raportów Pracowników Banku', "mod_history_cms_users");
INSERT INTO `module` (`name`, `remote`) VALUES	('Moduł Raportów Użytkowników aplikacji', "mod_history_mobile_users");

INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '1');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '2');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '3');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '4');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '5');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '6');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '7');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '8');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '9');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '10');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '11');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '12');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('1', '13');

INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('3', '1');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('3', '2');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('3', '3');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('3', '4');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('3', '5');
INSERT INTO `role_modules` (`role_id`, `module_id`) VALUES ('3', '6');

INSERT INTO `role_modules_unchecked` (`role_id`, `modules_unchecked_id`) VALUES ('1', '1');
INSERT INTO `role_modules_unchecked` (`role_id`, `modules_unchecked_id`) VALUES ('1', '2');
INSERT INTO `role_modules_unchecked` (`role_id`, `modules_unchecked_id`) VALUES ('1', '3');
INSERT INTO `role_modules_unchecked` (`role_id`, `modules_unchecked_id`) VALUES ('1', '4');