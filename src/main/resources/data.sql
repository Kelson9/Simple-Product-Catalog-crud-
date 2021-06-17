INSERT INTO roles(name) VALUES('ROLE_MANAGER');
INSERT INTO roles(name) VALUES('ROLE_CHEF');
INSERT INTO roles(name) VALUES('ROLE_WAITER');
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SUPPLIER');



-- 1 - ADMIN - MyPass
INSERT INTO users(username,email, password)
VALUES('kelson','kelsonngoe@gmail.com','godlove99@');

INSERT INTO users(username,email, password)
VALUES('kelson1','kelsonngoe1@gmail.com','kelson123');


INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);
