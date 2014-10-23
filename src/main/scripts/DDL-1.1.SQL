CREATE TABLE Usuario (
   email TEXT PRIMARY KEY
);

CREATE TABLE EstudoUsuario (
   id BIGINT PRIMARY KEY NOT NULL,
   email TEXT REFERENCES Usuario ( email ) NOT NULL,
   nome TEXT NOT NULL,
   fim DATE,
   UNIQUE ( email, nome )
);
