package com.sicredi.cooperativismo.domain.exception;

import java.time.LocalDateTime;

public class SessaoVotacaoPrazoPrecedeDataAtualException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoPrazoPrecedeDataAtualException(LocalDateTime prazo) {
		// bundle
		super(new StringBuilder("Prazo informado ").append(prazo).append(" precede a data atual.").toString());
	}

}
