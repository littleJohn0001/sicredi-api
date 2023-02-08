package com.sicredi.cooperativismo.domain.exception;

public class AssociadoJaRegistradoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public AssociadoJaRegistradoException(String cpf) {
		// bundle
		// informar de registro do usuario, falha de seguranca?
		super(new StringBuilder("Associado de cpf ").append(cpf).append(" já registrado.").toString());
	}

}
