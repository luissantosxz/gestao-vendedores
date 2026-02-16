CREATE TABLE vendedor (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    empresa_id UUID NOT NULL,
    situacao VARCHAR(20),

    CONSTRAINT fk_vendedor_empresa
        FOREIGN KEY (empresa_id)
        REFERENCES empresa(id)
);
