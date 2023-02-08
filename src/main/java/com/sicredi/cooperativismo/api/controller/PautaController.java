package com.sicredi.cooperativismo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.cooperativismo.api.model.PautaResponseDTO;
import com.sicredi.cooperativismo.domain.service.PautaService;

@RestController
@RequestMapping("/pautas")
public class PautaController {

	@Autowired
	private PautaService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PautaResponseDTO cadastrar() {
		return service.cadastrar();
	}
}
