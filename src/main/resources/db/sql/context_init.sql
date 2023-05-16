insert into role (id, name, description) VALUES (1, 'USER', 'Common user role in system');
insert into role (id, name, description) VALUES (2, 'ADMIN', 'Common admin role in system');
insert into role (id, name, description) VALUES (3, 'ANALYST', 'Common user role in system');

insert into users (id, username, password, firstname, lastname, patronymic, active, credentials_expired)
VALUES ('2730a9a2-d3bd-4ee4-bfde-c1259af67225', 'user', '$2a$12$GlUrTUomg3I5S8Covb3gZeyJ9c8jdNMcIOul1GJIrCXLrhK3LQlyG', 'Roman', 'Pushnoy', null, true, false);
insert into users (id, username, password, firstname, lastname, patronymic, active, credentials_expired)
VALUES ('4db9c437-7a49-4e61-8e4d-72dfa3388b25','admin', '$2a$12$87Rz8V0E2B6ZQfd7UiVOye2oG685oUnt1qmonwBwRf5l3Ml4VLDHi', 'Sergey', 'Admin', null, true, false);
insert into users (id, username, password, firstname, lastname, patronymic, active, credentials_expired)
VALUES ('086ef377-0afd-4e38-b940-a9f0af86458c','analyst', '$2a$12$87Rz8V0E2B6ZQfd7UiVOye2oG685oUnt1qmonwBwRf5l3Ml4VLDHi', 'Kirill', 'Analyzer', null, true, false);

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

insert into car_type_allowed_item_types (car_type_id, allowed_item_types_id) VALUES (1, 1);
insert into car_type_allowed_item_types (car_type_id, allowed_item_types_id) VALUES (2, 1);
insert into car_type_allowed_item_types (car_type_id, allowed_item_types_id) VALUES (3, 1);

insert into customer (id, firstname, lastname, patronymic, full_name, name_initials, phone, additional_phone, address)
VALUES ('02e04cb0-52c0-47c3-9fc0-d5c8ca9d842b', 'Иван', 'Иванов', 'Иванович', 'Иванов Иван Иванович', null, '89001002003', null, null);
insert into customer (id, firstname, lastname, patronymic, full_name, name_initials, phone, additional_phone, address)
VALUES ('51b667c9-1a7c-4892-8569-c4a10b235e5c', 'Роман', 'Пушной', 'Романович', 'Пушной Роман Романович', null, '88308575831', null, null);

insert into cargo (id, cargo_index, total_price, location_from, location_to, created_at, updated_at, currency_id, current_status_code, customer_id, source_company_id, car_type_id)
VALUES ('fd1f370c-e7f8-47ab-b7d2-3e2a6e27c450', '7370450177', 3000, 'г.Москва, ул. Безымянная 18', 'г.Владивосток, ул. Морская 65', current_date, null, 1, 10, '02e04cb0-52c0-47c3-9fc0-d5c8ca9d842b', null, 1);
insert into cargo_services (cargo_id, services_id) VALUES ('fd1f370c-e7f8-47ab-b7d2-3e2a6e27c450', 1);
