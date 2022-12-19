package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class RendaNaoEncontradaException2 extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public RendaNaoEncontradaException2(String message) {
		super(message);
		
	}
	
	public RendaNaoEncontradaException2(Long id) {
		this(String.format("A fonte de renda de código %d não existe", id));
	}

	
}
