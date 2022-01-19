--liquibase formatted sql
--changeset db:7

DROP TABLE IF EXISTS notes CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;
DROP TABLE IF EXISTS folders CASCADE;
DROP TABLE IF EXISTS user_notes CASCADE;


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
    authorities VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE folders (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR (255) NOT NULL,
    fk_author_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE notes (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR (255) NOT NULL,
    body TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_author_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    fk_folder_id BIGINT NOT NULL REFERENCES folders(id) ON DELETE CASCADE
);

CREATE TABLE user_notes (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    fk_user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    fk_note_id BIGINT NOT NULL REFERENCES notes(id) ON DELETE CASCADE,
    UNIQUE (fk_user_id, fk_note_id)
    -- CONSTRAINT not_owner CHECK (fk_user_id != (SELECT id FROM notes WHERE id = fk_note_id))
);


insert into users (username, password, enabled)values ('dolan', 'disney123', true);
insert into users (username, password, enabled) values ('taylor', 'swift13', true);
insert into users (username, password, enabled) values ('user1', 'pass1', true);


insert into users (username, password, enabled) values ('user2', 'pass2', true);
insert into users (username, password, enabled) values ('user3', 'pass3', true);
insert into users (username, password, enabled) values ('user4', 'pass4', true);
insert into users (username, password, enabled) values ('user5', 'pass5', true);
insert into users (username, password, enabled) values ('user6', 'pass16', true);

insert into folders (title, fk_author_id) values ('Dolan Notebook 1', 1);
insert into folders (title, fk_author_id) values ('Taylors Notebook 2', 2);
insert into folders (title, fk_author_id) values ('Notebook 3', 3);


insert into notes (fk_author_id, fk_folder_id, title, body) values (1, 1, 'Crab-eating raccoon', 'Procyon cancrivorus');
insert into notes (fk_author_id, fk_folder_id, title, body) values (2, 2, 'Asiatic wild ass', 'Equus hemionus');
insert into notes (fk_author_id, fk_folder_id, title, body) values (2, 2, 'Lemur, lesser mouse', 'Microcebus murinus');
insert into notes (fk_author_id, fk_folder_id, title, body) values (3, 3, 'Whale, long-finned pilot', 'Globicephala melas');
insert into notes (fk_author_id, fk_folder_id, title, body) values (3, 3, 'Orca', 'Orcinus orca');
insert into notes (fk_author_id, fk_folder_id, title, body) values (1, 1, 'Ringtail', 'Bassariscus astutus');
insert into notes (fk_author_id, fk_folder_id, title, body) values (2, 2, 'Macaw, blue and gold', 'Ara ararauna');
insert into notes (fk_author_id, fk_folder_id, title, body) values (3, 3, 'Arctic lemming', 'Dicrostonyx groenlandicus');
insert into notes (fk_author_id, fk_folder_id, title, body) values (1, 1, 'Yellow-throated sandgrouse', 'Pterocles gutturalis');
insert into notes (fk_author_id, fk_folder_id, title, body) values (2, 2, 'Squirrel, european red', 'Sciurus vulgaris');
insert into notes (fk_author_id, fk_folder_id, title, body) values (3, 3,'Squirrel, eastern fox', 'Sciurus niger');

insert into user_notes(fk_user_id, fk_note_id) values (5, 5);
insert into user_notes(fk_user_id, fk_note_id) values (4, 5);
insert into user_notes(fk_user_id, fk_note_id) values (3, 5);
insert into user_notes(fk_user_id, fk_note_id) values (5, 6);
insert into user_notes(fk_user_id, fk_note_id) values (5, 7);
insert into user_notes(fk_user_id, fk_note_id) values (5, 8);

-- insert into user_notes(fk_user_id, fk_note_id) values (1, 1);



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
values ('Donald', 'Duck', 'dolaN', 'disney123', 'donald@disney.com', 2);

insert into users (first_name, last_name, username, password, email, enabled)
values ('Mickey', 'Mouse', 'MickeyfromDisney', 'disney456', 'mickey@disney.com', 2);

insert into users (first_name, last_name, username, password, email, enabled)
values ('Goofy', 'Goof', 'g00byplz', 'disney789', 'goofy@disney.com', 2);

DROP TABLE IF EXISTS notes CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS fk_author_idities CASCADE;
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