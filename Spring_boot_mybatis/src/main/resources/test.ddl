drop table example.person;

CREATE TABLE example.person
(
idx INTEGER NOT NULL,
name VARCHAR(255),
age INTEGER,
address VARCHAR(255),
PRIMARY KEY (idx),
UNIQUE (name));

truncate TABLE example.person;



