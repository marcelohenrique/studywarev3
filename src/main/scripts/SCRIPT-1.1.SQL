ALTER TABLE UsuarioEstudo RENAME TO Estudo;

CREATE TABLE UsuarioEstudo (
    id BIGINT PRIMARY KEY,
    usuario TEXT NOT NULL REFERENCES Usuario ( email ),
    estudo BIGINT NOT NULL REFERENCES Estudo ( id )
);

INSERT INTO UsuarioEstudo ( id, usuario, estudo ) SELECT id, email, id FROM Estudo;

-- INSERT INTO UsuarioEstudo ( id, usuario, estudo ) VALUES ( 1003, 'marcelohenriquedeoliveiralima@gmail.com', 1001 );

ALTER TABLE Estudo DROP COLUMN email;

ALTER TABLE UsuarioEstudoMateria RENAME TO EstudoMateria;

ALTER TABLE EstudoMateria RENAME usuarioEstudo TO estudo;

ALTER TABLE UsuarioEstudoDiario RENAME TO EstudoDiario;

ALTER TABLE EstudoDiario RENAME usuarioEstudo TO estudo;

ALTER TABLE UsuarioEstudoMateriaHistorico RENAME TO EstudoMateriaHistorico;

ALTER TABLE EstudoMateriaHistorico RENAME usuarioEstudoMateria TO estudoMateria;

CREATE SEQUENCE EstudoSeq START 1001 NO CYCLE;

ALTER TABLE EstudoMateria DROP CONSTRAINT usuarioestudomateria_usuarioestudo_materia_key;

ALTER TABLE EstudoMateria ADD CONSTRAINT estudomateria_estudo_materia_ordem_unique UNIQUE ( estudo, materia, ordem );

ALTER TABLE EstudoMateriaHistorico ADD COLUMN estudo BIGINT;
ALTER TABLE EstudoMateriaHistorico ADD COLUMN materia BIGINT;
ALTER TABLE EstudoMateriaHistorico ADD FOREIGN KEY (estudo) REFERENCES Estudo(id);
ALTER TABLE EstudoMateriaHistorico ADD FOREIGN KEY (materia) REFERENCES Materia(id);

UPDATE EstudoMateriaHistorico SET estudo = em.estudo, materia = em.materia
  FROM ( SELECT em.id, em.estudo, em.materia FROM EstudoMateria em ) em WHERE estudoMateria = em.id;

ALTER TABLE Estudo ADD COLUMN inicio DATE;