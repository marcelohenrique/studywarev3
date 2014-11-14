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


ALTER TABLE public.dia OWNER TO adminz9euiyz;

--
-- Name: materia; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE materia (
    id bigint NOT NULL,
    sigla text NOT NULL,
    nome text NOT NULL
);


ALTER TABLE public.materia OWNER TO adminz9euiyz;

--
-- Name: materiaseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE materiaseq
    START WITH 1018
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.materiaseq OWNER TO adminz9euiyz;

--
-- Name: sq_historico_estudo; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE sq_historico_estudo
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sq_historico_estudo OWNER TO adminz9euiyz;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuario (
    email text NOT NULL,
    ativo boolean DEFAULT false NOT NULL
);


ALTER TABLE public.usuario OWNER TO adminz9euiyz;

--
-- Name: usuarioestudo; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuarioestudo (
    id bigint NOT NULL,
    nome text NOT NULL,
    fim date,
    email text NOT NULL
);


ALTER TABLE public.usuarioestudo OWNER TO adminz9euiyz;

--
-- Name: usuarioestudodiario; Type: TABLE; Schema: public; Owner: adminz9euiyz; Tablespace: 
--

CREATE TABLE usuarioestudodiario (
    usuarioestudo bigint NOT NULL,
    dia bigint NOT NULL,
    tempoalocado bigint NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.usuarioestudodiario OWNER TO adminz9euiyz;

--
-- Name: usuarioestudodiarioseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE usuarioestudodiarioseq
    START WITH 15
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarioestudodiarioseq OWNER TO adminz9euiyz;

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


ALTER TABLE public.usuarioestudomateria OWNER TO adminz9euiyz;

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


ALTER TABLE public.usuarioestudomateriahistorico OWNER TO adminz9euiyz;

--
-- Name: usuarioestudomateriaseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE usuarioestudomateriaseq
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarioestudomateriaseq OWNER TO adminz9euiyz;

--
-- Name: usuarioestudoseq; Type: SEQUENCE; Schema: public; Owner: adminz9euiyz
--

CREATE SEQUENCE usuarioestudoseq
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarioestudoseq OWNER TO adminz9euiyz;

--
-- Data for Name: dia; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY dia (id, nome, descricao) FROM stdin;
0  Domingo  Domingo
1  Segunda  Segunda-feira
2  Terça Terça-feira
3  Quarta   Quarta-feira
4  Quinta   Quinta-feira
5  Sexta Sexta-feira
6  Sábado   Sábado
\.


--
-- Data for Name: materia; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY materia (id, sigla, nome) FROM stdin;
1002  D. ADM   Direito Administrativo
1003  D. CONST Direito Constitucional
1004  MPU   Legislação aplicada ao MPU
1005  RH Relações Humanas
1006  ING   Inglês
1007  RED   Redação
1008  GGOV  Gestão e Governança de TI
1009  DEV   Desenvolvimento de Software
1010  ENG   Engenharia de Software
1011  SEG   Segurança da Informação
1012  BD Banco de Dados
1013  RL Raciocínio Lógico
1014  STF   Legislação Específica do STF
1015  SO Sistemas Operacionais
1016  REDES Redes de Computadores
1017  PROC NEG Processos de Negócio
1001  PORT  Língua Portuguesa
1018  CE Controle Externo
1019  FC Fundamentos de Computação
1020  AUDTI Auditoria de TI
\.


--
-- Name: materiaseq; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('materiaseq', 1020, true);


--
-- Name: sq_historico_estudo; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('sq_historico_estudo', 1079, true);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY usuario (email, ativo) FROM stdin;
eduardo.teodorus@gmail.com f
wilsonmacariano@gmail.com  f
mhguara@gmail.com t
\.


--
-- Data for Name: usuarioestudo; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY usuarioestudo (id, nome, fim, email) FROM stdin;
1001  MPU - 2013  2013-10-06  mhguara@gmail.com
1002  STF - 2013  2013-12-15  mhguara@gmail.com
\.


--
-- Data for Name: usuarioestudodiario; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY usuarioestudodiario (usuarioestudo, dia, tempoalocado, id) FROM stdin;
1001  0  10800000 1
1001  1  10800000 2
1001  2  10800000 3
1001  3  10800000 4
1001  4  10800000 5
1001  5  10800000 6
1001  6  21600000 7
1002  0  10800000 8
1002  1  10800000 9
1002  2  10800000 10
1002  3  10800000 11
1002  4  10800000 12
1002  5  10800000 13
1002  6  21600000 14
\.


--
-- Name: usuarioestudodiarioseq; Type: SEQUENCE SET; Schema: public; Owner: adminz9euiyz
--

SELECT pg_catalog.setval('usuarioestudodiarioseq', 21, true);


--
-- Data for Name: usuarioestudomateria; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY usuarioestudomateria (usuarioestudo, materia, tempoalocado, ordem, id) FROM stdin;
1001  1001  7200000  1  1001
1001  1002  7200000  3  1002
1001  1003  7200000  5  1003
1001  1004  7200000  7  1004
1001  1005  3600000  9  1005
1001  1006  3600000  12 1006
1001  1007  3600000  11 1007
1001  1008  9000000  2  1008
1001  1009  9000000  4  1009
1001  1010  10800000 6  1010
1001  1011  7200000  8  1011
1001  1012  10800000 10 1012
1002  1001  3600000  3  1013
1002  1002  7200000  1  1014
1002  1003  7200000  7  1015
1002  1006  1800000  5  1016
1002  1007  3600000  11 1017
1002  1008  7200000  9  1018
1002  1009  10800000 2  1019
1002  1010  3600000  10 1020
1002  1011  3600000  8  1021
1002  1012  5400000  6  1022
1002  1013  3600000  4  1023
1002  1014  5400000  13 1024
1002  1015  3600000  14 1025
1002  1016  3600000  12 1026
1002  1017  5400000  15 1027
\.


--
-- Data for Name: usuarioestudomateriahistorico; Type: TABLE DATA; Schema: public; Owner: adminz9euiyz
--

COPY usuarioestudomateriahistorico (id, horaestudo, tempoestudado, observacao, usuarioestudomateria) FROM stdin;
1001  2013-08-22 00:59:47  1330000     \N
1002  2013-08-24 15:39:36  1287000     \N
1003  2013-08-24 15:57:53  608000      \N
1004  2013-08-24 16:46:32  2617000     \N
1005  2013-08-24 18:47:56  3699000     \N
1006  2013-08-24 22:03:21  373000      \N
1007  2013-08-25 01:18:57  2646000     \N
1008  2013-08-25 20:26:49  3564000     \N
1009  2013-08-26 01:23:08  4294000     \N
1010  2013-08-26 02:32:10  296000      \N
1011  2013-08-27 01:39:46  3461000     \N
1012  2013-08-28 01:16:55  3742000     \N
1013  2013-08-28 01:34:52  774000      \N
1014  2013-08-29 02:16:42  791000      \N
1015  2013-08-30 01:03:17  1394000     \N
1016  2013-09-04 00:53:12  4570000     \N
1017  2013-09-06 22:53:01  1727000  Resolução de questões – Interpretação de texto  \N
1018  2013-09-08 13:04:16  3477000  Redação Oficial   \N
1019  2013-09-08 18:07:50  1543000  Revisão Itil-v3, questões PMBoK. \N
1020  2013-09-08 19:47:55  2544000  Questões CMMI, revisão IN 04/2010.  \N
1021  2013-09-08 22:08:51  2811000  Licitações – Estratégia Concursos   \N
1022  2013-09-08 23:55:39  2818000  PHP – Codecademy  \N
1023  2013-09-09 02:08:32  1443000  PHP – Codecademy  \N
1024  2013-09-09 22:54:02  3817000  Estratégia Concursos \N
1025  2013-09-10 01:04:59  1134000  Questões Mapa da Prova – Qualidade de Software  \N
1026  2013-09-10 23:33:31  2345000  APF / Questões Mapa da Prova – Qualidade de Software  \N
1027  2013-09-11 00:52:29  3562000  Estratégia Concursos \N
1028  2013-09-11 23:46:30  3471000  Questões Mapa da Prova  \N
1029  2013-09-12 01:10:26  859000   Questões Mapa da Prova – Inglês  \N
1030  2013-09-13 00:19:53  1838000  Questões Mapa da Prova – Inglês  \N
1031  2013-09-13 21:29:38  1726000  Questões Mapa da Prova – Inglês  \N
1032  2013-09-13 22:31:05  2369000  Cathedra – BD_04-AbordagemRelacional   \N
1033  2013-09-15 20:04:16  1335000  Questões Mapa da Prova – Redação Oficial  \N
1034  2013-09-17 23:17:28  941000   Costinha - Sintaxe   \N
1035  2013-09-17 23:57:50  1274000  Costinha - Sintaxe - Termos integrantes da oração  \N
1036  2013-09-18 01:11:18  2100000  Cathedra - PMBoK - Mapa da Prova \N
1037  2013-09-19 22:00:00  840000   Estratégia Concursos - Licitações   \N
1038  2013-09-22 13:55:09  2212000  Estratégia Concursos - Licitações   \N
1039  2013-09-22 15:00:22  1979000  Estratégia Concursos - Licitações   \N
1040  2013-09-22 16:34:01  3812000  PHP – Codecademy  \N
1041  2013-09-22 18:28:27  1868000  Estratégia Concursos - Aula 00   \N
1042  2013-09-23 01:12:13  1355000  Estratégia Concursos - Aula 00   \N
1043  2013-09-23 22:50:08  1857000  Mapa da Prova - UML  \N
1044  2013-09-24 23:52:59  2545000  Estratégia Concursos - Aula 01 - Pág. 7.  \N
1045  2013-09-25 23:23:20  1907000  Mapa da Prova  \N
1046  2013-09-26 00:25:11  2615000  SAD - Cathedra / Mapa da Prova   \N
1047  2013-09-26 22:30:37  3023000  Estratégia Concursos - Aula 01 - Terminei \N
1048  2013-09-26 23:43:00  2132000  Estratégia Concursos - Aula 02 - Pág. 51 (e) \N
1049  2013-10-01 00:58:53  602000   Estratégia Concursos - Aula 02 - Pág. 8.  \N
1050  2013-10-01 23:31:50  422000   Estratégia Concursos - Aula 02 - Pág. 10. \N
1051  2013-10-02 16:28:58  569000   Estratégia Concursos - Aula 02 - Pág. 13. \N
1052  2013-10-02 21:49:23  2902000  Estratégia Concursos - Aula 02 - Terminei \N
1053  2013-10-02 22:55:53  893000   Estratégia Concursos - Aula 02 - Pág. 53. \N
1054  2013-10-03 21:47:22  1442000  Estratégia Concursos - Aula 10 - Pág. 10. \N
1055  2013-10-03 21:56:08  475000   Mapa da Prova  \N
1056  2013-10-03 22:51:42  2404000  Estratégia Concursos - Aula 03 - Terminei.   \N
1057  2013-10-03 23:52:57  1585000  Estratégia Concursos - Aula 02 - Pág. 59. \N
1058  2013-10-04 15:20:22  936000   Mapa da Prova  \N
1059  2013-10-04 22:11:09  3573000  Estratégia Concursos - Aula 04 - Terminei.   \N
1060  2013-10-04 23:59:49  295000   Estratégia Concursos - Aula 05 - Pág 04.  \N
1061  2013-10-05 14:11:00  4451000  Estratégia Concursos - Aula 05 - Terminei.   \N
1062  2013-10-05 14:59:40  2848000  Estratégia Concursos - Aula 06 - Terminei.   \N
1063  2013-10-05 20:29:44  3968000  Estratégia Concursos - Aula 02 - Terminei.   \N
1064  2013-10-17 22:17:22  2217000  Estratégia Concursos - Aula 00 - Pág. 15. \N
1065  2013-10-22 23:55:47.716 816000   Estratégia Concursos - Aula 00 - Pág. 20. \N
1066  2013-10-23 21:41:29.056 2546000  Estratégia Concursos - Aula 00 - Pág. 34. \N
1067  2013-10-24 22:33:57.734 651000   Estratégia Concursos - Aula 00 - Pág. 38. \N
1068  2013-10-25 19:55:15.716 3730000  Mapa da Prova. \N
1069  2013-10-26 10:28:18.343 367000   Mapa da Prova. \N
1070  2013-10-26 23:10:59.368 1914000  Dominando TI - Ferramentas, servidores e frameworks.  \N
1071  2013-10-27 13:32:55.269 597000   Mapa da Prova. \N
1072  2013-10-27 14:06:42.254 467000   Dominando TI - Estruturas de Dados - Ordenação, pesquisa e hashing.  \N
1073  2013-10-28 21:28:52.223 2047000  Fernando Moura.   \N
1074  2013-10-28 22:31:10.339 540000   Mapa da Prova. \N
1075  2013-10-31 21:36:28.297 3387000  Mapa da Prova. \N
1076  2013-10-31 22:30:41.508 897000   Fernando Moura.   \N
1077  2013-11-01 22:48:37.843 2165000  Estratégia Concursos - Aula 00 - Pág. 44. \N
1078  2013-11-04 05:37:15.014 441000   Estratégia Concursos - Aula 00 - Pág. 47. \N
1079  2013-11-04 21:50:14.757 921000   Fernando Moura - Pág. 38.  \N
\.


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

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

