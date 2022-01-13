insert into role_model (id,active,code,name) values(1,1,'ROLE_USER','ROLE_USER');
insert into role_model (id,active,code,name) values(2,1,'ROLE_ADMIN','ROLE_ADMIN');

insert into permission_model (id,active,code,name) values(1,1,'VIEW','VIEW');

INSERT INTO user_model (id, account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES (1, 0, 0, 0, 'tamnd@gmail.com', 1,'NGUYEN DINH TAM', '{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i','tamnd')
