ALTER TABLE UsuarioEstudo RENAME TO Estudo;

CREATE TABLE UsuarioEstudo (
    id BIGINT PRIMARY KEY,
    usuario TEXT NOT NULL REFERENCES Usuario ( email ),
    estudo BIGINT NOT NULL REFERENCES Estudo ( id )
);

INSERT INTO UsuarioEstudo ( id, usuario, estudo ) SELECT id, email, id FROM Estudo;

INSERT INTO UsuarioEstudo ( id, usuario, estudo ) VALUES ( 1003, 'marcelohenriquedeoliveiralima@gmail.com', 1001 );

ALTER TABLE Estudo DROP COLUMN email;

ALTER TABLE UsuarioEstudoMateria RENAME TO EstudoMateria;

ALTER TABLE EstudoMateria RENAME usuarioEstudo TO estudo;

ALTER TABLE UsuarioEstudoDiario RENAME TO EstudoDiario;

ALTER TABLE EstudoDiario RENAME usuarioEstudo TO estudo;

ALTER TABLE UsuarioEstudoMateriaHistorico RENAME TO EstudoMateriaHistorico;

ALTER TABLE EstudoMateriaHistorico RENAME usuarioEstudoMateria TO estudoMateria;

CREATE SEQUENCE EstudoSeq START 1001 NO CYCLE;