package com.sicredi.cooperativismo.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.cooperativismo.api.model.AssociadoRequestDTO;
import com.sicredi.cooperativismo.api.model.AssociadoResponseDTO;
import com.sicredi.cooperativismo.domain.exception.AssociadoJaRegistradoException;
import com.sicredi.cooperativismo.domain.exception.AssociadoNaoEncontradaException;
import com.sicredi.cooperativismo.domain.model.Associado;
import com.sicredi.cooperativismo.infrastructure.repository.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;

	@Transactional
	public AssociadoResponseDTO criar(AssociadoRequestDTO request) {
		var cpf = request.getCpf();

		if (repository.existsAssociadoByCpf(cpf)) {
			throw new AssociadoJaRegistradoException(cpf);
		}

		var associado = repository.save(Associado.builder().cpf(cpf).build());

		return AssociadoResponseDTO.builder().id(associado.getId()).cpf(associado.getCpf()).build();
	}

	public Associado get(Long associadoId) {
		return repository.findById(associadoId).orElseThrow(() -> new AssociadoNaoEncontradaException(associadoId));
	}

}
