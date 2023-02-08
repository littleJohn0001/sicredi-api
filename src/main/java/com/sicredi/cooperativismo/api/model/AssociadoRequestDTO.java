package com.sicredi.cooperativismo.api.model;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AssociadoRequestDTO {

	//TODO criar converter para validacao
	@NotEmpty
	private String cpf;

}
