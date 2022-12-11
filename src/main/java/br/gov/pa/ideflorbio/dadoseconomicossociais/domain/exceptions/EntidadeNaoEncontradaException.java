package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class EntidadeNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

}
