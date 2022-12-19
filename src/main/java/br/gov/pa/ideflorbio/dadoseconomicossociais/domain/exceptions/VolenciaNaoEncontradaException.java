package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class VolenciaNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public VolenciaNaoEncontradaException(String message) {
		super(message);
		
	}
	
	public VolenciaNaoEncontradaException(Long id) {
		this(String.format("Os dados sobre violencia de código %d não existem", id));
	}

	
}
