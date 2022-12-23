package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class InstituicaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public InstituicaoNaoEncontradaException(String message) {
		super(message);
		
	}
	
	public InstituicaoNaoEncontradaException(Long id) {
		this(String.format("Dados de instituição de código %d não existem", id));
	}

	
}
