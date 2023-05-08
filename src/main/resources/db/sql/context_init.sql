insert into role (id, name, description) VALUES (1, 'USER', 'Common user role in system');
insert into role (id, name, description) VALUES (2, 'ADMIN', 'Common admin role in system');
insert into role (id, name, description) VALUES (3, 'ANALYST', 'Common user role in system');

insert into users (id, username, password, firstname, lastname, patronymic, active, credentials_expired) VALUES ('2730a9a2-d3bd-4ee4-bfde-c1259af67225', 'user', '$2a$12$GlUrTUomg3I5S8Covb3gZeyJ9c8jdNMcIOul1GJIrCXLrhK3LQlyG', 'Roman', 'Pushnoy', null, true, false);
insert into users (id, username, password, firstname, lastname, patronymic, active, credentials_expired) VALUES ('4db9c437-7a49-4e61-8e4d-72dfa3388b25','admin', '$2a$12$87Rz8V0E2B6ZQfd7UiVOye2oG685oUnt1qmonwBwRf5l3Ml4VLDHi', 'Sergey', 'Admin', null, true, false);
insert into users (id, username, password, firstname, lastname, patronymic, active, credentials_expired) VALUES ('086ef377-0afd-4e38-b940-a9f0af86458c','analyst', '$2a$12$87Rz8V0E2B6ZQfd7UiVOye2oG685oUnt1qmonwBwRf5l3Ml4VLDHi', 'Kirill', 'Analyzer', null, true, false);

insert into users_roles (user_id, roles_id) VALUES ('2730a9a2-d3bd-4ee4-bfde-c1259af67225', 1);
insert into users_roles (user_id, roles_id) VALUES ('4db9c437-7a49-4e61-8e4d-72dfa3388b25', 2);
insert into users_roles (user_id, roles_id) VALUES ('086ef377-0afd-4e38-b940-a9f0af86458c', 3);

insert into service (id, code, name, price, description) VALUES (1, 'DELIVERY_TO_DOOR', 'Доставка до дома клиента', 200, null);

insert into item_type (id, code, name, description) VALUES (1, 'LIGHT_WEIGHT_ITEM', 'Лёгкие предметы', null);

insert into currency (id, code, country) VALUES (1, 'RUB', 'Российская Федерация');

insert into cargo_status (id, status_code, status_name, description) VALUES (1, 10, 'Заказ создан', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (2, 20, 'Заказ собирается', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (3, 30, 'Заказ в пути', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (4, 40, 'Заказ прибыл на склад', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (5, 50, 'Сортировка', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (6, 55, 'Заказ готов к выдаче', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (7, 60, 'Доставка клиенту на дом', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (8, 70, 'Заказ выдан клиенту', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (9, 75, 'Заказ выдан на возврат', null);
insert into cargo_status (id, status_code, status_name, description) VALUES (10, 80, 'Заказ возвращён', null);

insert into car_type (id, code, name, max_weight) VALUES (1, 'LIGHT_CAR', 'Лёгкий автомобиль', 50);
insert into car_type (id, code, name, max_weight) VALUES (2, 'LIGHT_TRUCK', 'Лёгкий грузовик', 1000);
insert into car_type (id, code, name, max_weight) VALUES (3, 'TRUCK', 'Фура', 20000);
