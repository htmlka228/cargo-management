create table if not exists car_type
(
    id         bigint       not null
        primary key,
    code       varchar(255) not null,
    name       varchar(255),
    max_weight integer      not null

);

create table if not exists cargo_status
(
    id          bigint       not null
        primary key,
    status_code integer
        constraint uk_jf3us2p1oydlruib0c086gu7j
            unique           not null,
    status_name varchar(255) not null,
    description varchar(255)
);

create table if not exists company
(
    id      uuid not null default gen_random_uuid()
        primary key,
    address varchar(255),
    code    varchar(255),
    country varchar(255),
    email   varchar(255),
    inn     varchar(255),
    kpp     varchar(255),
    name    varchar(255),
    phone   varchar(255),
    zipcode varchar(255)
);

create table if not exists currency
(
    id      bigint not null
        primary key,
    code    varchar(255),
    country varchar(255)
);

create table if not exists customer
(
    id               uuid not null default gen_random_uuid()
        primary key,
    additional_phone varchar(255),
    address          varchar(255),
    firstname        varchar(255),
    full_name        varchar(255),
    lastname         varchar(255),
    name_initials    varchar(255),
    patronymic       varchar(255),
    phone            varchar(255)
);

create table if not exists cargo
(
    id                  uuid not null default gen_random_uuid()
        primary key,
    cargo_index         varchar(255),
    created_at          timestamp(6),
    location_from       varchar(255),
    location_to         varchar(255),
    total_price         integer,
    updated_at          timestamp(6),
    currency_id         bigint
        constraint fk6egmiys1t23fi1oyyvbbsq92u
            references currency,
    current_status_code integer
        constraint fk57fjk1nkncckm4babi40v58v0
            references cargo_status (status_code),
    customer_id         uuid
        constraint fkrni7fkrrkjamxvj3hdanmet4d
            references customer,
    source_company_id   uuid
        constraint fk1yln6001wixajbwl9xhsxcl0n
            references company,
    car_type_id         bigint
        constraint fkijxi0yxpxqawwa1yb9gx1tsmw
            references car_type
);

create table if not exists item_type
(
    id          bigint       not null
        primary key,
    code        varchar(255) not null,
    name        varchar(255) not null,
    description varchar(255)
);

create table if not exists car_type_allowed_item_types
(
    car_type_id           bigint not null
        constraint fkm1h1kfnmof5y08qv9veqr3yvr
            references car_type,
    allowed_item_types_id bigint not null
        constraint fkj60sphc06n7vtpdy8kgkbvsgw
            references item_type,
    primary key (car_type_id, allowed_item_types_id)
);

create table if not exists item
(
    id           uuid not null default gen_random_uuid()
        primary key,
    name         varchar(255),
    price        integer,
    item_type_id bigint
        constraint fkh7kyk389qj2m5chaa0njsq601
            references item_type
);

create table if not exists role
(
    id          bigint       not null
        primary key,
    name        varchar(255) not null,
    description varchar(255)

);

create table if not exists service
(
    id          bigint       not null
        primary key,
    code        varchar(255) not null,
    name        varchar(255) not null,
    price       integer      not null,
    description varchar(255)
);
create table if not exists cargo_services
(
    cargo_id    uuid   not null
        constraint fkbe8fmwixuwa9hac28673u5u7w
            references cargo,
    services_id bigint not null
        constraint fk8sokwsffdbifjtbrlognwxder
            references service,
    primary key (cargo_id, services_id)
);

create table if not exists users
(
    id                  uuid         not null
        primary key,
    username            varchar(255) not null,
    password            varchar(255) not null,
    firstname           varchar(255) not null,
    lastname            varchar(255) not null,
    patronymic          varchar(255),
    active              boolean      not null,
    credentials_expired boolean      not null
);

create table if not exists users_roles
(
    user_id  uuid   not null
        constraint fk2o0jvgh89lemvvo17cbqvdxaa
            references users,
    roles_id bigint not null
        constraint fk15d410tj6juko0sq9k4km60xq
            references role,
    primary key (user_id, roles_id)
);
