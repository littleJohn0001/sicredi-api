package com.sicredi.cooperativismo.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.cooperativismo.domain.model.Pauta;
import com.sicredi.cooperativismo.domain.model.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

	Optional<SessaoVotacao> findByPauta(Pauta pauta);

}
