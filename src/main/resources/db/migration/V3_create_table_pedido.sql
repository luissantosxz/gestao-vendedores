CREATE TABLE pedido(
    id UUID PRIMARY KEY,
    vendedor_id UUID NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL,
    data_pedido TIMESTAMP NOT NULL,

    CONSTRAINT fk_pedido_vendedor
        FOREIGN KEY (vendedor_id)
        REFERENCES vendedor(id)
);