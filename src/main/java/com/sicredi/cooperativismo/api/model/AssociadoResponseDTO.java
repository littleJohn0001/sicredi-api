package com.sicredi.cooperativismo.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AssociadoResponseDTO {
	
	private Long id;
	
	private String cpf;

}
