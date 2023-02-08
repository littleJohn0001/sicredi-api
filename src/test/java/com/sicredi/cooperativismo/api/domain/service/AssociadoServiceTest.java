package com.sicredi.cooperativismo.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sicredi.cooperativismo.api.model.AssociadoRequestDTO;
import com.sicredi.cooperativismo.domain.exception.AssociadoJaRegistradoException;
import com.sicredi.cooperativismo.domain.model.Associado;
import com.sicredi.cooperativismo.domain.service.AssociadoService;
import com.sicredi.cooperativismo.infrastructure.repository.AssociadoRepository;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoServiceTest {
	
	@InjectMocks
	private AssociadoService service;
	
	@Mock
	private AssociadoRepository repository;
	
	@Test
	public void deveCriarUmAssociado() {
		var associadoRequest = AssociadoRequestDTO.builder().cpf("123").build();
		var associadoResponse = Associado.builder().id(1L).cpf("123").build();
		
		doReturn(associadoResponse).when(repository).save(Mockito.any());
		var response = service.criar(associadoRequest);
		
		assertThat(response.getId()).isNotNull();
	}
	
	@Test(expected = AssociadoJaRegistradoException.class)
	public void deveLancarUmaAssociadoJaRegistradoException() {
		var associadoRequest = AssociadoRequestDTO.builder().cpf("123").build();
		
		doReturn(true).when(repository).existsAssociadoByCpf(Mockito.any());
		service.criar(associadoRequest);
		
	}

}
