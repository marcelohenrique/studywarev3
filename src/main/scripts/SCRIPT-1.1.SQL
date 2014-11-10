ALTER TABLE UsuarioEstudoMateria ADD COLUMN id BIGINT;

CREATE SEQUENCE UsuarioEstudoMateriaSeq START WITH 1001;

UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1001;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1002;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1003;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1004;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1005;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1006;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1007;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1008;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1009;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1010;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1011;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1001 AND materia=1012;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1001;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1002;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1003;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1006;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1007;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1008;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1009;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1010;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1011;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1012;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1013;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1014;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1015;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1016;
UPDATE UsuarioEstudoMateria SET id=NEXTVAL( 'UsuarioEstudoMateriaSeq' ) WHERE usuarioestudo=1002 AND materia=1017;

ALTER TABLE UsuarioEstudoMateria ALTER COLUMN id SET NOT NULL;
ALTER TABLE UsuarioEstudoMateria DROP CONSTRAINT usuarioestudomateria_pkey;
ALTER TABLE UsuarioEstudoMateria ADD PRIMARY KEY ( id );
ALTER TABLE UsuarioEstudoMateria ADD UNIQUE ( usuarioEstudo, materia );

ALTER TABLE UsuarioEstudoMateriaHistorico ADD COLUMN usuarioEstudoMateria BIGINT;

UPDATE UsuarioEstudoMateriaHistorico SET usuarioEstudoMateria = ( SELECT id FROM UsuarioEstudoMateria WHERE UsuarioEstudoMateria.usuarioEstudo = UsuarioEstudoMateriaHistorico.usuarioEstudo and UsuarioEstudoMateria.materia = UsuarioEstudoMateriaHistorico.materia );
ALTER TABLE UsuarioEstudoMateriaHistorico ADD FOREIGN KEY ( usuarioEstudoMateria ) REFERENCES UsuarioEstudoMateria ( id );
ALTER TABLE UsuarioEstudoMateriaHistorico DROP COLUMN usuarioEstudo;
ALTER TABLE UsuarioEstudoMateriaHistorico DROP COLUMN materia;

CREATE SEQUENCE UsuarioEstudoSeq START WITH 1001;

ALTER TABLE UsuarioEstudoMateria ADD UNIQUE ( usuarioEstudo, ordem );