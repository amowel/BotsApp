CREATE TABLE users
(
  username VARCHAR(50) PRIMARY KEY NOT NULL,
  password VARCHAR(20)             NOT NULL,
  enabled  INTEGER                 NOT NULL
);