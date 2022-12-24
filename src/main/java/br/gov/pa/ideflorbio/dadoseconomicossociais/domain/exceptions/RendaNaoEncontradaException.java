package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class RendaNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public RendaNaoEncontradaException(String message) {
		super(message);
		
	}
	
	public RendaNaoEncontradaException(Long id) {
		this(String.format("A fonte de renda de código %d não existe", id));
	}

	
}
