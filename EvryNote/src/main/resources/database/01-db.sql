--liquibase formatted sql
--changeset db:4

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
    lastmodify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

insert into users (username, password, enabled)
values ('dolan', 'disney123', true);

insert into users (username, password, enabled)
values ('taylor', 'swift13', true);


insert into notes (title, body, author) values ('Pocket gopher (unidentified)', 'unavailable', 1);
insert into notes (title, body, author) values ('Mississippi alligator', 'Alligator mississippiensis', 1);
insert into notes (title, body, author) values ('African ground squirrel (unidentified)', 'Xerus sp.', 1);
insert into notes (title, body, author) values ('African darter', 'Anhinga rufa', 2);
insert into notes (title, body, author) values ('Grey-footed squirrel', 'Paraxerus cepapi', 1);
insert into notes (title, body, author) values ('Lynx, african', 'Felis caracal', 2);
insert into notes (title, body, author) values ('Porcupine, crested', 'Hystrix cristata', 2);
insert into notes (title, body, author) values ('Elephant, african', 'Loxodonta africana', 1);
insert into notes (title, body, author) values ('Otter, african clawless', 'Aonyx capensis', 2);
insert into notes (title, body, author) values ('Marten, american', 'Martes americana', 1);
insert into notes (title, body, author) values ('Swallow (unidentified)', 'unavailable', 1);
insert into notes (title, body, author) values ('Vulture, bengal', 'Gyps bengalensis', 1);
insert into notes (title, body, author) values ('Kangaroo, red', 'Macropus rufus', 2);
insert into notes (title, body, author) values ('American bison', 'Bison bison', 2);
insert into notes (title, body, author) values ('Black kite', 'Milvus migrans', 2);
insert into notes (title, body, author) values ('Zorro, azara''s', 'Pseudalopex gymnocercus', 2);
insert into notes (title, body, author) values ('Canada goose', 'Branta canadensis', 2);
insert into notes (title, body, author) values ('Macaw, blue and gold', 'Ara ararauna', 1);
insert into notes (title, body, author) values ('Rhinoceros, square-lipped', 'Ceratotherium simum', 1);
insert into notes (title, body, author) values ('Dove, white-winged', 'Zenaida asiatica', 2);
insert into notes (title, body, author) values ('Falcon, peregrine', 'Falco peregrinus', 1);
insert into notes (title, body, author) values ('Sociable weaver', 'Philetairus socius', 1);
insert into notes (title, body, author) values ('Black-cheeked waxbill', 'Estrilda erythronotos', 2);
insert into notes (title, body, author) values ('Common wallaroo', 'Macropus robustus', 2);
insert into notes (title, body, author) values ('Southern brown bandicoot', 'Isoodon obesulus', 2);
insert into notes (title, body, author) values ('Grey phalarope', 'Phalaropus fulicarius', 1);
insert into notes (title, body, author) values ('Arctic fox', 'Alopex lagopus', 2);
insert into notes (title, body, author) values ('Curlew, black', 'Haematopus ater', 1);
insert into notes (title, body, author) values ('Wambenger, red-tailed', 'Phascogale calura', 1);
insert into notes (title, body, author) values ('Pheasant, ring-necked', 'Phasianus colchicus', 1);
insert into notes (title, body, author) values ('Fairy penguin', 'Eudyptula minor', 2);
insert into notes (title, body, author) values ('Red phalarope', 'Phalaropus fulicarius', 1);
insert into notes (title, body, author) values ('Lion, galapagos sea', 'Zalophus californicus', 1);
insert into notes (title, body, author) values ('Lesser flamingo', 'Phoeniconaias minor', 2);
insert into notes (title, body, author) values ('Mouse, four-striped grass', 'Rhabdomys pumilio', 1);
insert into notes (title, body, author) values ('Snowy sheathbill', 'Chionis alba', 1);
insert into notes (title, body, author) values ('Ring-tailed possum', 'Pseudocheirus peregrinus', 1);
insert into notes (title, body, author) values ('Herring gull', 'unavailable', 1);
insert into notes (title, body, author) values ('Arctic lemming', 'Dicrostonyx groenlandicus', 1);
insert into notes (title, body, author) values ('Jungle kangaroo', 'Macropus agilis', 2);
insert into notes (title, body, author) values ('Stork, white-necked', 'Ciconia episcopus', 2);
insert into notes (title, body, author) values ('Iguana, marine', 'Amblyrhynchus cristatus', 2);
insert into notes (title, body, author) values ('Dik, kirk''s dik', 'Madoqua kirkii', 2);
insert into notes (title, body, author) values ('Gecko, bent-toed', 'Cyrtodactylus louisiadensis', 1);
insert into notes (title, body, author) values ('Bateleur eagle', 'Terathopius ecaudatus', 2);
insert into notes (title, body, author) values ('Two-toed sloth', 'Choloepus hoffmani', 2);
insert into notes (title, body, author) values ('Southern black-backed gull', 'Larus dominicanus', 1);
insert into notes (title, body, author) values ('Marten, american', 'Martes americana', 1);
insert into notes (title, body, author) values ('Lemur, sportive', 'Lepilemur rufescens', 2);
insert into notes (title, body, author) values ('Lesser masked weaver', 'Ploceus intermedius', 2);
insert into notes (title, body, author) values ('African red-eyed bulbul', 'Pycnonotus nigricans', 2);
insert into notes (title, body, author) values ('Chipmunk, least', 'Eutamias minimus', 2);
insert into notes (title, body, author) values ('Green-backed heron', 'Butorides striatus', 1);
insert into notes (title, body, author) values ('Baboon, yellow', 'Papio cynocephalus', 1);
insert into notes (title, body, author) values ('Nelson ground squirrel', 'Ammospermophilus nelsoni', 2);
insert into notes (title, body, author) values ('Deer, red', 'Cervus elaphus', 1);
insert into notes (title, body, author) values ('American beaver', 'Castor canadensis', 1);
insert into notes (title, body, author) values ('Sociable weaver', 'Philetairus socius', 2);
insert into notes (title, body, author) values ('Mule deer', 'Odocoileus hemionus', 2);
insert into notes (title, body, author) values ('Savannah deer (unidentified)', 'unavailable', 2);
insert into notes (title, body, author) values ('Squirrel, palm', 'Funambulus pennati', 2);
insert into notes (title, body, author) values ('Killer whale', 'Orcinus orca', 1);
insert into notes (title, body, author) values ('Turaco, violet-crested', 'Tauraco porphyrelophus', 2);
insert into notes (title, body, author) values ('Capuchin, weeper', 'Cebus nigrivittatus', 2);
insert into notes (title, body, author) values ('Trotter, lily', 'Actophilornis africanus', 2);
insert into notes (title, body, author) values ('Chacma baboon', 'Papio ursinus', 2);
insert into notes (title, body, author) values ('Red-capped cardinal', 'Paroaria gularis', 1);
insert into notes (title, body, author) values ('Mouflon', 'Ovis musimon', 1);
insert into notes (title, body, author) values ('Kangaroo, black-faced', 'Macropus fuliginosus', 2);
insert into notes (title, body, author) values ('Woylie', 'Bettongia penicillata', 1);
insert into notes (title, body, author) values ('Rainbow lory', 'Trichoglossus haematodus moluccanus', 2);
insert into notes (title, body, author) values ('Numbat', 'Myrmecobius fasciatus', 1);
insert into notes (title, body, author) values ('Wild boar', 'Sus scrofa', 2);
insert into notes (title, body, author) values ('Curve-billed thrasher', 'Toxostoma curvirostre', 2);
insert into notes (title, body, author) values ('Paddy heron (unidentified)', 'unavailable', 1);
insert into notes (title, body, author) values ('Gulls (unidentified)', 'Larus sp.', 2);
insert into notes (title, body, author) values ('Deer, red', 'Cervus elaphus', 1);
insert into notes (title, body, author) values ('Black-winged stilt', 'Himantopus himantopus', 1);
insert into notes (title, body, author) values ('Racer snake', 'Coluber constrictor', 2);
insert into notes (title, body, author) values ('Indian porcupine', 'Hystrix indica', 2);
insert into notes (title, body, author) values ('Cockatoo, slender-billed', 'Cacatua tenuirostris', 1);
insert into notes (title, body, author) values ('Crowned hawk-eagle', 'Spizaetus coronatus', 1);
insert into notes (title, body, author) values ('Cormorant, flightless', 'Nannopterum harrisi', 1);
insert into notes (title, body, author) values ('Osprey', 'Pandon haliaetus', 1);
insert into notes (title, body, author) values ('Cat, tiger', 'Dasyurus maculatus', 1);
insert into notes (title, body, author) values ('Stork, saddle-billed', 'Ephipplorhynchus senegalensis', 2);
insert into notes (title, body, author) values ('Macaw, blue and yellow', 'Ara ararauna', 2);
insert into notes (title, body, author) values ('Barking gecko', 'Phylurus milli', 1);
insert into notes (title, body, author) values ('Gecko (unidentified)', 'unavailable', 1);
insert into notes (title, body, author) values ('Kelp gull', 'Larus dominicanus', 1);
insert into notes (title, body, author) values ('Crane, blue', 'Anthropoides paradisea', 1);
insert into notes (title, body, author) values ('Small Indian mongoose', 'Herpestes javanicus', 2);
insert into notes (title, body, author) values ('Lourie, grey', 'Lorythaixoides concolor', 2);
insert into notes (title, body, author) values ('Skimmer, four-spotted', 'Libellula quadrimaculata', 2);
insert into notes (title, body, author) values ('Waterbuck, common', 'Kobus defassa', 2);
insert into notes (title, body, author) values ('Mara', 'Dolichitus patagonum', 1);
insert into notes (title, body, author) values ('Goose, canada', 'Branta canadensis', 1);
insert into notes (title, body, author) values ('Netted rock dragon', 'Ctenophorus ornatus', 2);
insert into notes (title, body, author) values ('Bushpig', 'Potamochoerus porcus', 1);
insert into notes (title, body, author) values ('Ornate rock dragon', 'Ctenophorus ornatus', 2);


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