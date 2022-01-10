--liquibase formatted sql
--changeset db:2

DROP TABLE IF EXISTS notes CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;



CREATE TABLE users
(
    id BIGSERIAL NOT NULL UNIQUE,
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE notes (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR (255) NOT NULL,
    body TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

insert into users (username, password, enabled)
values ('dolan', 'disney123', true);

insert into users (username, password, enabled)
values ('taylor', 'swift13', true);

insert into notes (title, body, author)
values ('Lover', 'We could leave the Christmas lights up til January
And this is our place, we make the rules
And there s a dazzling haze, a mysterious way about you dear
Have I known you 20 seconds or 20 years?', 2);

insert into notes (title, body, author)
values ('Shopping List', 'NOTHING AT ALL', 1);

insert into notes (title, body, author)
values ('Goofy on Vacation', 'HAHAHAHAHAAHAHAHA', 1);




/*
CREATE TABLE users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(150),
    last_name VARCHAR(150),
    username VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    email VARCHAR(200) UNIQUE,
    enabled BOOLEAN NOT NULL
);

insert into users (first_name, last_name, username, password, email, enabled)
values ('Donald', 'Duck', 'dolaN', 'disney123', 'donald@disney.com', true);

insert into users (first_name, last_name, username, password, email, enabled)
values ('Mickey', 'Mouse', 'MickeyfromDisney', 'disney456', 'mickey@disney.com', true);

insert into users (first_name, last_name, username, password, email, enabled)
values ('Goofy', 'Goof', 'g00byplz', 'disney789', 'goofy@disney.com', true);

DROP TABLE IF EXISTS notes CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;
DROP TABLE IF EXISTS user_notes CASCADE;

CREATE TABLE user_notes (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    fk_user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    fk_note_id BIGINT NOT NULL REFERENCES notes(id) ON DELETE CASCADE,
    UNIQUE (fk_user_id, fk_note_id)
);
insert into user_notes (fk_user_id, fk_note_id)
values (1, 1);
*/