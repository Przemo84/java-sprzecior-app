ALTER DATABASE nordgeo_db COLLATE 'utf8_general_ci';
-- --
INSERT INTO role (name) VALUES ('Admin');
INSERT INTO role (name) VALUES ('Editor');
INSERT INTO role (name) VALUES ('Employee');

-- --
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Marcin', 'Oprzyński', 'm.oprzynski@nordgeo.pl', 'm.oprzynski', '', 3, 1, 0);
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Adam', 'Kierznikiewicz', 'a.kierznikiewicz@nordgeo.pl', 'a.kierznikiewicz', '', 3, 1, 0);
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('Michał', 'Skolimowski', 'm.skolimowski@nordgeo.pl', 'm.skolimowski', '', 3, 1, 0);

-- --
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('001n', 'TS 15 1" R1000','Tachimetr', '1616299', null, null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('013n', 'CS 15', 'Robot', '2900371', '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('018n', 'GS 08', 'GPS Antena', '1731239', '2015-01-01 00:00:00', '2015-01-01 00:00:00' ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('022n', 'CS 10', 'GPS Kontroller', '2521958', '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('025n', 'DNA 03', 'Niwelator', '330148', null, null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('035n', 'GEB222', 'Bateria do Instrumentu', '10698', '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('053n', 'GEB212', 'Bateria do Robota', '10173', null, null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('070n', 'GEB212', 'Bateria do GPS', '11124', null, null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('090n', 'GST120-9', 'Statyw', null, '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('101n', 'GMP111', 'Małe Lusterko', null, '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('115n', 'GPR121', 'Duże Lustro', '8839877', '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('128n', 'GHT63', 'Tyczka do Robota', null, null, null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('133n', 'GLS30', 'Tyczka do GPS', null, null, null ,1)