package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class EntrevistadorNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public EntrevistadorNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public EntrevistadorNaoEncontradoException(Long id) {
		this(String.format("Entrevistador de código %d não existe", id));
	}

	
}
