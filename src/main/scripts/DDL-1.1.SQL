CREATE TABLE Usuario (
   email TEXT PRIMARY KEY
);

CREATE TABLE EstudoUsuario (
   id BIGINT NOT NULL,
   email TEXT REFERENCES Usuario ( email ) NOT NULL,
   nome TEXT NOT NULL,
   fim DATE,
   PRIMARY KEY ( id, email )
);

CREATE SEQUENCE EstudoUsuarioSeq;