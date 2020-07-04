ALTER DATABASE docker COLLATE 'utf8_general_ci';

SET FOREIGN_KEY_CHECKS=0;
DELETE FROM action_history;

INSERT INTO action_history (action, create_date, module, subject_id, user_id)
VALUES ('action', '2018-02-03 09:23:54', 'mod', '12', 1);
SET FOREIGN_KEY_CHECKS=1;