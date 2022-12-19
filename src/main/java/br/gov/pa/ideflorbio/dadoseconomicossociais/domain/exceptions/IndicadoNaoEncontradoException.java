package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class IndicadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public IndicadoNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public IndicadoNaoEncontradoException(Long id) {
		this(String.format("O indicado de código %d não existe", id));
	}

	
}
