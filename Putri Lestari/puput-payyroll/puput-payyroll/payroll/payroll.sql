CREATE DATABASE payroll;
USE payroll;

INSERT INTO `user` (`username`,`password`,`enabled`) VALUES 
	('employee','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
	('accountant','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
	('manager','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);
	
INSERT INTO `role` (NAME) VALUES 
	('ROLE_EMPLOYEE'),('ROLE_ACCOUNTANT'),('ROLE_MANAGER');
	
INSERT INTO `users_roles` (user_id,role_id) VALUES 
	(1, 1),
	(2, 1),
	(2, 2),
	(3, 1),
	(3, 2),
	(3, 3);
	
	
DROP TABLE POSITION;
DROP TABLE employee;
DROP TABLE absent;