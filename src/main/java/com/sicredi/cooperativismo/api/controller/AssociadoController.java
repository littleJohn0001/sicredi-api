package com.sicredi.cooperativismo.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sicredi.cooperativismo.api.model.AssociadoRequestDTO;
import com.sicredi.cooperativismo.api.model.AssociadoResponseDTO;
import com.sicredi.cooperativismo.domain.service.AssociadoService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssociadoResponseDTO criar(@RequestBody @Valid AssociadoRequestDTO request) {
		return service.criar(request);
	}

}