package com.sicredi.cooperativismo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.cooperativismo.domain.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

}
