create table Author(
    id          UUID primary key                        not null,
    name        varchar                                 not null,
    version     text                                    not null,
    create_at   TIMESTAMP default CURRENT_TIMESTAMP     not null,
    updated_at  TIMESTAMP ON UPDATE CURRENT_TIMESTAMP   nullable
);