--create schema if not exists PUBLIC;
create table PERSON (
    ID int not null,
    NAME varchar(100) not null,
    PRIMARY KEY (`ID`)
);