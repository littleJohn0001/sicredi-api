package com.sicredi.cooperativismo.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sicredi.cooperativismo.api.model.AssociadoRequestDTO;
import com.sicredi.cooperativismo.api.model.AssociadoResponseDTO;
import com.sicredi.cooperativismo.domain.service.AssociadoService;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoControllerTest {
	
	@InjectMocks
	private AssociadoController controller;
	
	@Mock
	private AssociadoService service;
	
	@Test
	public void deveCriarUmAssociado() {
		var associadoRequest = AssociadoRequestDTO.builder().cpf("123").build();
		var associadoResponse = AssociadoResponseDTO.builder().id(1L).cpf("123").build();
		
		when(service.criar(associadoRequest)).thenReturn(associadoResponse);
		var response = controller.criar(associadoRequest);
		
		assertThat(response.getId()).isNotNull();
	}

}
