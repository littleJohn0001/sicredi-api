package com.sicredi.cooperativismo.domain.exception;

public class PautaNaoEncontradaException extends RecursoNaoEncontradoException {
	
	private static final long serialVersionUID = 1L;

	public PautaNaoEncontradaException(Long pautaId) {
		//bundle
		super(new StringBuilder("Pauta de id ").append(pautaId).append(" não econtrada.").toString());
	}

}
