package com.sicredi.cooperativismo.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.cooperativismo.api.model.PautaResponseDTO;
import com.sicredi.cooperativismo.domain.exception.PautaNaoEncontradaException;
import com.sicredi.cooperativismo.domain.model.Pauta;
import com.sicredi.cooperativismo.infrastructure.repository.PautaRepository;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository repository;

	@Transactional
	public PautaResponseDTO cadastrar() {
		var pauta = repository.save(new Pauta());

		//modelMapper
		return PautaResponseDTO.builder().id(pauta.getId()).build();
	}

	public Pauta get(Long pautaId) {
		return repository.findById(pautaId).orElseThrow(() -> new PautaNaoEncontradaException(pautaId));
	}

}
