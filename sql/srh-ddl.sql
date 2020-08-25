create table if not exists recommendation_result
(
    id                serial not null
        constraint recommendation_result_pkey
            primary key,
    offline           boolean,
    passing_score     double precision,
    recommendation_id integer
);

create table if not exists recommendation_result_matrix
(
    id            serial not null
        constraint recommendation_result_matrix_pkey
            primary key,
    offline       boolean,
    passing_score double precision,
    algorithm_id  integer
);

create table if not exists admin
(
    id       serial not null
        constraint admin_pkey
            primary key,
    email    varchar(255),
    login    varchar(255)
        constraint uk_3x1e18lmu2r0xekpql9xme0pp
            unique,
    name     varchar(255),
    password varchar(255)
);

create table if not exists algorithm
(
    id                  serial not null
        constraint algorithm_pkey
            primary key,
    name                varchar(255),
    type_recommendation integer
);

create table if not exists api_user
(
    id       serial not null
        constraint api_user_pkey
            primary key,
    email    varchar(255),
    login    varchar(255)
        constraint uk_4t225igbfxdh2nwrdxl9bj6sm
            unique,
    name     varchar(255),
    password varchar(255)
);

create table if not exists attribute
(
    id    serial not null
        constraint attribute_pkey
            primary key,
    name  varchar(255),
    type  integer,
    value varchar(255)
);

create table if not exists evaluator
(
    id       serial not null
        constraint evaluator_pkey
            primary key,
    email    varchar(255),
    login    varchar(255)
        constraint uk_aj2nbjcktr4hwcholt622v59t
            unique,
    name     varchar(255),
    password varchar(255)
);

create table if not exists profile
(
    id   serial not null
        constraint profile_pkey
            primary key,
    name varchar(255)
);

create table if not exists api_user_profiles
(
    api_user_id integer not null
        constraint fkpx6vfuuwp66l3g3on32v71im6
            references api_user,
    profiles_id integer not null
        constraint fkfaqquqdj3wln9qhxpjlnh2207
            references profile
);

create table if not exists profile_api_users
(
    profile_id   integer not null
        constraint fkswf9x26bns3yo2r4t92qreyy1
            references profile,
    api_users_id integer not null
        constraint fk9r7fc6gp5q9pqdmaghfitllmj
            references api_user
);

create table if not exists project
(
    id             serial not null
        constraint project_pkey
            primary key,
    date           date,
    description    varchar(255),
    last_matrix_id integer,
    name           varchar(255),
    situation      varchar(255),
    visible        boolean,
    admin_id       integer
        constraint fkcgoacd8dcusw1n4aojuwydj6l
            references admin
);

create table if not exists evaluator_projects
(
    evaluators_id integer not null
        constraint fkiysfeu8g857sdjdeoec1wv1u4
            references evaluator,
    projects_id   integer not null
        constraint fkkv4xqrwmqcggmx5mlpvcv0hrr
            references project
);

create table if not exists tag
(
    id   serial not null
        constraint tag_pkey
            primary key,
    name varchar(255)
        constraint uk_1wdpsed5kna2y38hnbgrnhi5b
            unique
);

create table if not exists type_item
(
    id   serial not null
        constraint type_item_pkey
            primary key,
    name varchar(255)
        constraint uk_4be7c2ud8wx9k5nkglal1m7u8
            unique
);

create table if not exists attribute_type_itens
(
    attribute_id  integer not null
        constraint fk6bs6s1wqgt1kgtnr9bcg2t26l
            references attribute,
    type_itens_id integer not null
        constraint fkhjus0q3vyfr8oebwwsland6o1
            references type_item
);

create table if not exists item
(
    id           serial not null
        constraint item_pkey
            primary key,
    description  varchar(255),
    name         varchar(255),
    project_id   integer
        constraint fkf60hnjyqgladtp0jw5o0n4e9u
            references project,
    type_item_id integer
        constraint fkpfuaa5cmj6f4qc3mcja7u9m8s
            references type_item
);

create table if not exists attribute_itens
(
    attributes_id integer not null
        constraint fkjaw2m4gisd1r1wik7qrm4wlp
            references attribute,
    itens_id      integer not null
        constraint fkkdrvdj2wlkyn35oj1b2ie8lo4
            references item
);

create table if not exists item_rating
(
    id           serial not null
        constraint item_rating_pkey
            primary key,
    date         timestamp,
    score        double precision,
    evaluator_id integer
        constraint fkkrm8xumqdv1skhlxcinpyx2su
            references evaluator,
    item_id      integer
        constraint fkbvrotevbbbmsufdhp8e7rjlr6
            references item
);

create table if not exists recommendation
(
    id                 serial not null
        constraint recommendation_pkey
            primary key,
    date               timestamp,
    matrix_id          integer,
    runtime_in_seconds integer,
    weight             double precision,
    algorithm_id       integer
        constraint fkgr4ari5wvc3qif72cc9ls10hp
            references algorithm,
    evaluator_id       integer
        constraint fkt39540w01gugmixduamo1qbjw
            references evaluator,
    item_id            integer
        constraint fkt3e1ehy56xxeju40srit78qhd
            references item
);

create table if not exists recommendation_rating
(
    id                serial not null
        constraint recommendation_rating_pkey
            primary key,
    date              timestamp,
    score             double precision,
    evaluator_id      integer
        constraint fk5wx2s0hhqmwp8afu2ug7osg14
            references evaluator,
    recommendation_id integer
        constraint fkawtmt13ulmiw947s0v7mjyfv
            references recommendation
);

create table if not exists tag_itens
(
    tags_id  integer not null
        constraint fkk86yuwxwqn559jgpnkphv27rx
            references tag,
    itens_id integer not null
        constraint fk7q5xytm0n5i9p7w5f4qpltoko
            references item
);

create table if not exists type_item_attributes
(
    type_item_id  integer not null
        constraint fk7osebqe3myg2pdqgl5u46sc9k
            references type_item,
    attributes_id integer not null
        constraint fkddhvfyh7sffhnlewlnmr2cf8b
            references attribute
);

insert into algorithm (id, name, type_recommendation) values (1, 'Filtragem Híbrida Mista - Multi Thread', 0);

insert into api_user (id, email, login, name, password) values (1, 'admin@email.com', 'admin', 'admin', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
insert into api_user (id, email, login, name, password) values (2, 'admin@email.com', 'client', 'client', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

insert into profile (id, name) values (1, 'ROLE_ADMIN');
insert into profile (id, name) values (2, 'ROLE_USER');

insert into api_user_profiles (api_user_id, profiles_id) values (1, 1);
insert into api_user_profiles (api_user_id, profiles_id) values (1, 2);
insert into api_user_profiles (api_user_id, profiles_id) values (2, 2);