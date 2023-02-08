create table voto (
    id                bigint       not null,
    decisao_voto      varchar(255) not null,
    associado_id      bigint       not null,
    sessao_votacao_id bigint       not null,
    primary key (id),
    constraint voto_associado_fk foreign key (associado_id) references associado,
    constraint voto_sessao_votacao_fk foreign key (sessao_votacao_id) references sessao_votacao,
    constraint voto_associado_sessao_votacao_unique UNIQUE(associado_id, sessao_votacao_id)
);

create sequence if not exists voto_seq;