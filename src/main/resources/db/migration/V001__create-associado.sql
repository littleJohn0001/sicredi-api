create table associado (
    id  bigint       not null,
    cpf varchar(255) not null,
    primary key (id),
    constraint associado_cpf unique (cpf)
);

create sequence if not exists associado_seq;