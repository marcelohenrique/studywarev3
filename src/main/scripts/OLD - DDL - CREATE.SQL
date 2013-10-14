CREATE TABLE "public"."tb_banca"
(
   id int PRIMARY KEY NOT NULL,
   sigla text NOT NULL,
   nome text NOT NULL,
   descricao text
);
CREATE UNIQUE INDEX tb_banca_sigla_key ON "public"."tb_banca"(sigla);
CREATE UNIQUE INDEX tb_banca_pkey ON "public"."tb_banca"(id);

CREATE TABLE "public"."tb_orgao"
(
   id int PRIMARY KEY NOT NULL,
   sigla text NOT NULL,
   nome text NOT NULL,
   descricao text,
   site text
);
CREATE UNIQUE INDEX tb_orgao_sigla_key ON "public"."tb_orgao"(sigla);
CREATE UNIQUE INDEX tb_orgao_pkey ON "public"."tb_orgao"(id);

CREATE TABLE "public"."tb_concurso"
(
   id_orgao int NOT NULL,
   id_banca int NOT NULL,
   ano int NOT NULL,
   CONSTRAINT tb_concurso_pkey PRIMARY KEY (id_orgao,id_banca,ano)
);
ALTER TABLE "public"."tb_concurso" ADD CONSTRAINT tb_concurso_id_orgao_fkey FOREIGN KEY (id_orgao) REFERENCES "public"."tb_orgao"(id);
ALTER TABLE "public"."tb_concurso" ADD CONSTRAINT tb_concurso_id_banca_fkey FOREIGN KEY (id_banca) REFERENCES "public"."tb_banca"(id);
CREATE UNIQUE INDEX tb_concurso_pkey ON "public"."tb_concurso"
(
  id_orgao,
  id_banca,
  ano
);

CREATE TABLE "public"."tb_materia"
(
   id int PRIMARY KEY NOT NULL,
   sigla text NOT NULL,
   nome text NOT NULL,
   descricao text
);
CREATE UNIQUE INDEX tb_materia_sigla_key ON "public"."tb_materia"(sigla);
CREATE UNIQUE INDEX tb_materia_pkey ON "public"."tb_materia"(id);
