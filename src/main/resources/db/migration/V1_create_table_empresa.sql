CREATE TABLE empresa (
    id UUID PRIMARY KEY,
    razao_social VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NOT NULL UNIQUE,
    situacao VARCHAR(50)
);
