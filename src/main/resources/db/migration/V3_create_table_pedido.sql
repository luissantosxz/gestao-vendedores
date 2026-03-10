CREATE SEQUENCE SEQUENCE_PEDIDO START WITH 1 INCREMENT BY 1;

CREATE TABLE pedido(
    id INTEGER PRIMARY KEY,
    vendedor_id INTEGER NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL,
    data_pedido TIMESTAMP NOT NULL,

    CONSTRAINT fk_pedido_vendedor
        FOREIGN KEY (vendedor_id)
        REFERENCES vendedor(id)
);