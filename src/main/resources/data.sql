INSERT IGNORE INTO users VALUES 
	('user', '$2a$10$t2lI54B6jRiR8jPGh3fE/Oy.SWF/Y.MWriUkghfWyq9m9wMmerlky', 1),
	('manager', '$2a$10$t2lI54B6jRiR8jPGh3fE/Oy.SWF/Y.MWriUkghfWyq9m9wMmerlky', 1)
;

INSERT IGNORE INTO authorities VALUES 
	('user', 'ROLE_USER'),
	('manager', 'ROLE_MANAGER')
;