package com.sicredi.cooperativismo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.cooperativismo.domain.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
