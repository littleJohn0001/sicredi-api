package com.sicredi.cooperativismo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.cooperativismo.domain.model.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

	boolean existsAssociadoByCpf(String cpf);

}
