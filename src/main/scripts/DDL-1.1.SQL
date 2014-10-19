CREATE TABLE Usuario (
   email TEXT PRIMARY KEY
);

CREATE TABLE EstudoUsuario (
   email TEXT REFERENCES Usuario ( email ) NOT NULL,
   nome TEXT NOT NULL,
   fim DATE,
   PRIMARY KEY ( email, nome )
);
