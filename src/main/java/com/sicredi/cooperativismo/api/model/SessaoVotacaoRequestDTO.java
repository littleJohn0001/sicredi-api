package com.sicredi.cooperativismo.api.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessaoVotacaoRequestDTO {

	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
	private LocalDateTime prazo;
}
