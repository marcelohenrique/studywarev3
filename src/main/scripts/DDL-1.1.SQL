--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: dia; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE dia (
    id bigint NOT NULL,
    nome text NOT NULL,
    descricao text NOT NULL
);


--ALTER TABLE public.dia OWNER TO adminz9euiyz;

--
-- Name: materia; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE materia (
    id bigint NOT NULL,
    sigla text NOT NULL,
    nome text NOT NULL
);


--ALTER TABLE public.materia OWNER TO adminz9euiyz;

--
-- Name: materiaseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE materiaseq
    START WITH 1018
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.materiaseq OWNER TO adminz9euiyz;

--
-- Name: sq_historico_estudo; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE sq_historico_estudo
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.sq_historico_estudo OWNER TO adminz9euiyz;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuario (
    email text NOT NULL,
    ativo boolean DEFAULT false NOT NULL
);


--ALTER TABLE public.usuario OWNER TO adminz9euiyz;

--
-- Name: usuarioestudo; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuarioestudo (
    id bigint NOT NULL,
    nome text NOT NULL,
    fim date,
    email text NOT NULL
);


--ALTER TABLE public.usuarioestudo OWNER TO adminz9euiyz;

--
-- Name: usuarioestudodiario; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuarioestudodiario (
    usuarioestudo bigint NOT NULL,
    dia bigint NOT NULL,
    tempoalocado bigint NOT NULL,
    id bigint NOT NULL
);


--ALTER TABLE public.usuarioestudodiario OWNER TO adminz9euiyz;

--
-- Name: usuarioestudodiarioseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE usuarioestudodiarioseq
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.usuarioestudodiarioseq OWNER TO adminz9euiyz;

--
-- Name: usuarioestudomateria; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuarioestudomateria (
    usuarioestudo bigint NOT NULL,
    materia bigint NOT NULL,
    tempoalocado bigint NOT NULL,
    ordem bigint NOT NULL,
    id bigint NOT NULL
);


--ALTER TABLE public.usuarioestudomateria OWNER TO adminz9euiyz;

--
-- Name: usuarioestudomateriahistorico; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuarioestudomateriahistorico (
    id bigint NOT NULL,
    horaestudo timestamp without time zone NOT NULL,
    tempoestudado bigint NOT NULL,
    observacao text NOT NULL,
    usuarioestudomateria bigint
);


--ALTER TABLE public.usuarioestudomateriahistorico OWNER TO adminz9euiyz;

--
-- Name: usuarioestudomateriaseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE usuarioestudomateriaseq
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.usuarioestudomateriaseq OWNER TO adminz9euiyz;

--
-- Name: usuarioestudoseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE usuarioestudoseq
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE public.usuarioestudoseq OWNER TO adminz9euiyz;


--
-- Name: materiaseq; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('materiaseq', 1020, true);


--
-- Name: sq_historico_estudo; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('sq_historico_estudo', 1079, true);


--
-- Name: usuarioestudodiarioseq; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('usuarioestudodiarioseq', 21, true);


--
-- Name: usuarioestudomateriaseq; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('usuarioestudomateriaseq', 1053, true);


--
-- Name: usuarioestudoseq; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('usuarioestudoseq', 1002, true);


--
-- Name: tb_dia_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY dia
    ADD CONSTRAINT tb_dia_pkey PRIMARY KEY (id);


--
-- Name: tb_estudo_dia_id_estudo_id_dia_key; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudodiario
    ADD CONSTRAINT tb_estudo_dia_id_estudo_id_dia_key UNIQUE (usuarioestudo, dia);


--
-- Name: tb_estudo_materia_id_estudo_id_materia_key; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudomateria
    ADD CONSTRAINT tb_estudo_materia_id_estudo_id_materia_key UNIQUE (usuarioestudo, materia);


--
-- Name: tb_estudo_nome_key; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudo
    ADD CONSTRAINT tb_estudo_nome_key UNIQUE (nome);


--
-- Name: tb_estudo_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudo
    ADD CONSTRAINT tb_estudo_pkey PRIMARY KEY (id);


--
-- Name: tb_historico_estudo_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudomateriahistorico
    ADD CONSTRAINT tb_historico_estudo_pkey PRIMARY KEY (id);


--
-- Name: tb_materia_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY materia
    ADD CONSTRAINT tb_materia_pkey PRIMARY KEY (id);


--
-- Name: tb_materia_sigla_key; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY materia
    ADD CONSTRAINT tb_materia_sigla_key UNIQUE (sigla);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (email);


--
-- Name: usuarioestudodiario_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudodiario
    ADD CONSTRAINT usuarioestudodiario_pkey PRIMARY KEY (id);


--
-- Name: usuarioestudomateria_pkey; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudomateria
    ADD CONSTRAINT usuarioestudomateria_pkey PRIMARY KEY (id);


--
-- Name: usuarioestudomateria_usuarioestudo_materia_key; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudomateria
    ADD CONSTRAINT usuarioestudomateria_usuarioestudo_materia_key UNIQUE (usuarioestudo, materia);


--
-- Name: usuarioestudomateria_usuarioestudo_ordem_key; Type: CONSTRAINT; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

ALTER TABLE ONLY usuarioestudomateria
    ADD CONSTRAINT usuarioestudomateria_usuarioestudo_ordem_key UNIQUE (usuarioestudo, ordem);


--
-- Name: estudousuario_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminz9euiyz
--

ALTER TABLE ONLY usuarioestudo
    ADD CONSTRAINT estudousuario_email_fkey FOREIGN KEY (email) REFERENCES usuario(email);


--
-- Name: tb_estudo_dia_id_dia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminz9euiyz
--

ALTER TABLE ONLY usuarioestudodiario
    ADD CONSTRAINT tb_estudo_dia_id_dia_fkey FOREIGN KEY (dia) REFERENCES dia(id);


--
-- Name: tb_estudo_dia_id_estudo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminz9euiyz
--

ALTER TABLE ONLY usuarioestudodiario
    ADD CONSTRAINT tb_estudo_dia_id_estudo_fkey FOREIGN KEY (usuarioestudo) REFERENCES usuarioestudo(id);


--
-- Name: tb_estudo_materia_id_estudo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminz9euiyz
--

ALTER TABLE ONLY usuarioestudomateria
    ADD CONSTRAINT tb_estudo_materia_id_estudo_fkey FOREIGN KEY (usuarioestudo) REFERENCES usuarioestudo(id);


--
-- Name: tb_estudo_materia_id_materia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminz9euiyz
--

ALTER TABLE ONLY usuarioestudomateria
    ADD CONSTRAINT tb_estudo_materia_id_materia_fkey FOREIGN KEY (materia) REFERENCES materia(id);


--
-- Name: usuarioestudomateriahistorico_usuarioestudomateria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminz9euiyz
--

ALTER TABLE ONLY usuarioestudomateriahistorico
    ADD CONSTRAINT usuarioestudomateriahistorico_usuarioestudomateria_fkey FOREIGN KEY (usuarioestudomateria) REFERENCES usuarioestudomateria(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

--REVOKE ALL ON SCHEMA public FROM PUBLIC;
--REVOKE ALL ON SCHEMA public FROM postgres;
--GRANT ALL ON SCHEMA public TO postgres;
--GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

