package com.sicredi.cooperativismo.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.cooperativismo.api.model.SessaoVotacaoRequestDTO;
import com.sicredi.cooperativismo.api.model.SessaoVotacaoResponseDTO;
import com.sicredi.cooperativismo.domain.service.SessaoVotacaoService;

@RestController
@RequestMapping("/sessoes-votacao")
public class SessaoVotacaoController {
	
	@Autowired
	private SessaoVotacaoService service;
	
	@PostMapping("/{pautaId}")
	@ResponseStatus(HttpStatus.CREATED)
	public SessaoVotacaoResponseDTO abrir(@PathVariable Long pautaId,
			@RequestBody @Valid SessaoVotacaoRequestDTO request) {
		return service.abrir(pautaId, request);
	}

}
