package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class CreditoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public CreditoNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public CreditoNaoEncontradoException(Long id) {
		this(String.format("Crédito de código %d não existe", id));
	}

	
}
