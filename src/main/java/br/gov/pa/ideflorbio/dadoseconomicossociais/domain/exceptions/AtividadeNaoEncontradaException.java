package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class AtividadeNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public AtividadeNaoEncontradaException(String message) {
		super(message);
		
	}
	
	public AtividadeNaoEncontradaException(Long id) {
		this(String.format("Atividade de código %d não existe", id));
	}

	
}
