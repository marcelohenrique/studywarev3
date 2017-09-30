CREATE TABLE Banca (
    id bigint NOT NULL,
    nome text NOT NULL,
    descricao text NOT NULL,
    site text NOT NULL
);

CREATE SEQUENCE BancaSequence START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE TABLE orgao (
    id bigint NOT NULL,
    nome text NOT NULL,
    descricao text NOT NULL
);