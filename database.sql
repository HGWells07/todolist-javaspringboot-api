DROP DATABASE IF exists todolistexample;
CREATE DATABASE todolistexample;
USE todolistexample;
CREATE TABLE Tasks (
    Id varchar(36) NOT NULL,
    TaskName varchar(255),
    Details varchar(1024),
    Done bit,
    DueDate date,
    PRIMARY KEY (Id)
);
