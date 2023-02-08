create table sessao_votacao (
    id       bigint    not null,
    prazo    timestamp not null,
    pauta_id bigint    not null,
    primary key (id),
    constraint sessao_votacao_fk foreign key (pauta_id) references pauta
);

create sequence if not exists sessao_votacao_seq;