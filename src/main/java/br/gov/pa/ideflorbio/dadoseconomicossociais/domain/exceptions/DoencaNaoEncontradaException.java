package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class DoencaNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public DoencaNaoEncontradaException(String message) {
		super(message);
		
	}
	
	public DoencaNaoEncontradaException(Long id) {
		this(String.format("A doença de código %d não existe", id));
	}

	
}
