package com.sicredi.cooperativismo.api.model;

import com.sicredi.cooperativismo.domain.model.DecisaoVoto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VotoResponseDTO {
	
	private Long id;
	
	private DecisaoVoto decisaoVoto; 
	
	private AssociadoResponseDTO associado;
	
	private SessaoVotacaoResponseDTO sessaoVotacao;

}
