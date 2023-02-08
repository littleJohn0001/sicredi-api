package com.sicredi.cooperativismo.api.model;

import javax.validation.constraints.NotNull;

import com.sicredi.cooperativismo.domain.model.DecisaoVoto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VotoRequestDTO {
	
	@NotNull
	private Long associadoId;
	
	//validar enum
	private DecisaoVoto decisaoVoto;

}
