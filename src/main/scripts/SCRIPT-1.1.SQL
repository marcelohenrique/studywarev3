ALTER TABLE TB_ESTUDO_MATERIA RENAME TO UsuarioEstudoMateria;

ALTER TABLE EstudoUsuario RENAME TO UsuarioEstudo;

ALTER TABLE TB_HISTORICO_ESTUDO RENAME TO UsuarioEstudoMateriaHistorico;
ALTER TABLE UsuarioEstudoMateriaHistorico ADD COLUMN usuarioEstudo BIGINT;
ALTER TABLE UsuarioEstudoMateriaHistorico ADD COLUMN materia BIGINT;
UPDATE usuarioestudomateriahistorico
   SET usuarioestudo = UEM.ID_ESTUDO, materia = UEM.ID_MATERIA
  FROM usuarioestudomateria UEM
 WHERE usuarioestudomateriahistorico.ID_ESTUDO_MATERIA = UEM.ID
ALTER TABLE usuarioestudomateriahistorico DROP CONSTRAINT tb_historico_estudo_id_estudo_materia_fkey;

ALTER TABLE usuarioestudomateria DROP CONSTRAINT tb_estudo_materia_pkey;
ALTER TABLE usuarioestudomateria RENAME COLUMN ID_ESTUDO TO usuarioEstudo;
ALTER TABLE usuarioestudomateria RENAME COLUMN ID_MATERIA TO materia;
ALTER TABLE usuarioestudomateria ADD PRIMARY KEY ( usuarioEstudo, materia );

ALTER TABLE usuarioestudomateriahistorico ADD FOREIGN KEY ( usuarioestudo, materia ) REFERENCES usuarioestudomateria ( usuarioestudo, materia );

ALTER TABLE usuarioestudomateria DROP COLUMN ID;

ALTER TABLE usuarioestudomateriahistorico DROP COLUMN id_estudo_materia;