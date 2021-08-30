create table pedido (
    id bigserial not null,
    cliente varchar(36) not null,
    mesa Bool not null,
    endereco varchar(256) not null,
    pedido text not null,
    horario varchar(128) not null,
    descricao text,
    entregue Bool not null
);