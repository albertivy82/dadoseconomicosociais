package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class InsituticaoNaoEncontradaException2 extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public InsituticaoNaoEncontradaException2(String message) {
		super(message);
		
	}
	
	public InsituticaoNaoEncontradaException2(Long id) {
		this(String.format("Dados de instituição de código %d não existem", id));
	}

	
}
