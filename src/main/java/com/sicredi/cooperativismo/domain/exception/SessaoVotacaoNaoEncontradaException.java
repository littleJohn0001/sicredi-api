package com.sicredi.cooperativismo.domain.exception;

import com.sicredi.cooperativismo.domain.model.Pauta;

public class SessaoVotacaoNaoEncontradaException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoNaoEncontradaException(Long sessaoVotacaoId) {
		//bundle
		super(new StringBuilder("Sessão de votação de id ").append(sessaoVotacaoId).append(" não econtrada.").toString());
	}

	public SessaoVotacaoNaoEncontradaException(Pauta pauta) {
		super(new StringBuilder("Não foi encontrada uma sessão de votação para a pauta ").append(pauta.getId())
				.toString());
	}
}
