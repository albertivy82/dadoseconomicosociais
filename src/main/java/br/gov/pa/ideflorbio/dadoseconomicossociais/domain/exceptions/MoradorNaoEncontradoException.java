package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class MoradorNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public MoradorNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public MoradorNaoEncontradoException(Long id) {
		this(String.format("O morador de código %d não existe", id));
	}

	
}
