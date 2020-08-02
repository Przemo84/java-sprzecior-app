ALTER DATABASE docker COLLATE 'utf8_general_ci';
-- --
INSERT INTO role (name) VALUES ('Administrator');
INSERT INTO role (name) VALUES ('Pracownik');
-- --
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Marcin', 'Oprzyński', 'm.oprzynski@nordgeo.pl', 'm.oprzynski', '', 2, 1, 0);
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Adam', 'Kierznikiewicz', 'a.kierznikiewicz@nordgeo.pl', 'a.kierznikiewicz', '', 2, 1, 0);
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Michał', 'Skolimowski', 'm.skolimowski@nordgeo.pl', 'm.skolimowski', '', 2, 1, 0);
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Marta', 'Reszetow', 'm.reszetow@nordgeo.pl', 'm.reszetow', '', 2, 1, 0);
-- --
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc001', 'Statyw Leica ciężki','Statyw', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc002', 'Tachimetr Lecia TS12','Tachimetr', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc003', 'Tachimetr Lecia TS15','Tachimetr', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc004', 'Tachimetr Lecia 1012','Tachimetr', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc005', 'Niwelator precyzyjny Leica 506','Nivelator', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc006', 'Niwelator precyzyjny Leica 1002','Nivelator', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('GD 34234', 'VW Caddy Szary','Samochód', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc007', 'Lustro 2,5" Leica','Lustro', 1)
INSERT INTO tool (company_id, title, tool_type, available) VALUES ('abc008', 'Lustro 2,5" Leica','Lustro', 1)