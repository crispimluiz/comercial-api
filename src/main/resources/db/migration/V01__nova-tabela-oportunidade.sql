create table oportunidade(
    id bigint auto_increment not null,
    nome_prospecto VARCHAR (80) not null,
    descricao VARCHAR(200) not null,
    valor DECIMAL(10,2),

    PRIMARY KEY (id)
);