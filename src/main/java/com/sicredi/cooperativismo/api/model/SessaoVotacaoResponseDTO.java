package com.sicredi.cooperativismo.api.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessaoVotacaoResponseDTO {

	private Long id;
	
	private LocalDateTime prazo;
	
	private PautaResponseDTO pauta;
}
