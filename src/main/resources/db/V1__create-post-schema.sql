create table post(
    idAuthor   UUID,
    title      text not null,
    content    text not null,
    createdAt  TIMESTAMP default CURRENT_TIMESTAMP,
    modifiedAt TIMESTAMP
);