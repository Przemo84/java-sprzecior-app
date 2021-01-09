ALTER DATABASE nordgeo_db COLLATE 'utf8_general_ci';
-- --
INSERT INTO role (name) VALUES ('Admin');
INSERT INTO role (name) VALUES ('Editor');
INSERT INTO role (name) VALUES ('Employee');

-- --
INSERT INTO `user` (first_name, last_name, email, username, password, role_id, active, login_attempts) VALUES ('John', 'Doe', 'j.doe@nordgeo.pl', 'j.doe', '', 3, 1, 0);

-- --
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('001n', 'TS 15 1" R1000','Tachimetr', '1616299', null, null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('002n', 'CS 15', 'Robot', '2900371', '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('003n', 'GS 08', 'GPS Antena', '1731239', '2015-01-01 00:00:00', '2015-01-01 00:00:00' ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('004n', 'CS 10', 'GPS Kontroller', '2521958', '2015-01-01 00:00:00', null ,1)
INSERT INTO tool (company_id, model, tool_type, serial_no, production_date, calibration_date, available) VALUES ('005n', 'DNA 03', 'Niwelator', '330148', null, null ,1)
