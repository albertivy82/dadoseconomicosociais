package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class MoradorNaoEncontradoException2 extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public MoradorNaoEncontradoException2(String message) {
		super(message);
		
	}
	
	public MoradorNaoEncontradoException2(Long id) {
		this(String.format("O morador de código %d não existe", id));
	}

	
}
