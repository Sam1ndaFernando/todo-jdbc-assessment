CREATE DATABASE todo;
USE todo;

CREATE TABLE users
(
    uid      INT         PRIMARY KEY,
    name     VARCHAR(155) NOT NULL,
    password TEXT         NOT NULL
);

CREATE TABLE tasks
(
    tid         INT         PRIMARY KEY,
    description TEXT        NOT NULL,
    dueDate     DATE        NOT NULL,
    isCompleted TINYINT     NOT NULL,
    email       VARCHAR(35) NOT NULL,
    uid         INT         NOT NULL,
    FOREIGN KEY (uid) REFERENCES users (uid)
);
