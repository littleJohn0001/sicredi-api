package com.sicredi.cooperativismo.domain.exception;

import com.sicredi.cooperativismo.domain.model.Pauta;

public class SessaoVotacaoPrazoExpiradoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoPrazoExpiradoException(Pauta pauta) {
		// bundle
		super(new StringBuilder("O prazo de votação para a pauta ").append(pauta.getId()).append(" está expirado.")
				.toString());
	}

}
