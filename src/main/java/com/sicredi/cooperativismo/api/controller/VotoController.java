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

import com.sicredi.cooperativismo.api.model.VotoRequestDTO;
import com.sicredi.cooperativismo.api.model.VotoResponseDTO;
import com.sicredi.cooperativismo.domain.service.VotoService;

@RestController
@RequestMapping("/votos")
public class VotoController {
	
	@Autowired
	private VotoService service;
	
	@PostMapping("votar/{pautaId}")
	@ResponseStatus(HttpStatus.CREATED)
	public VotoResponseDTO votar(@PathVariable Long pautaId, @RequestBody @Valid VotoRequestDTO request) {
		return service.votar(pautaId, request);
	}

}
