create table USER
(
    ID           INTEGER not null,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(100),
    TOKEN        CHAR(36),
    GMT_CREAT    BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
