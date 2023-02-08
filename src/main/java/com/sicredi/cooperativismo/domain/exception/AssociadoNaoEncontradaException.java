package com.sicredi.cooperativismo.domain.exception;

public class AssociadoNaoEncontradaException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public AssociadoNaoEncontradaException(Long associadoId) {
		// bundle
		super(new StringBuilder("Associado de id ").append(associadoId).append(" não econtrado.").toString());
	}


}
