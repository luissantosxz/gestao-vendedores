CREATE SEQUENCE SEQUENCE_VENDEDOR START WITH 1 INCREMENT BY 1;

CREATE TABLE vendedor (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    empresa_id INTEGER NOT NULL,
    situacao VARCHAR(20),

    CONSTRAINT fk_vendedor_empresa
        FOREIGN KEY (empresa_id)
        REFERENCES empresa(id)
);