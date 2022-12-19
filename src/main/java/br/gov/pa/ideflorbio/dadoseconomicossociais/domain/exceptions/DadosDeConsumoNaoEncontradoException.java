package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions;

public class DadosDeConsumoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public DadosDeConsumoNaoEncontradoException(String message) {
		super(message);
		
	}
	
	public DadosDeConsumoNaoEncontradoException(Long id) {
		this(String.format("Os dados de consumo cadastrados com código %d não existem", id));
	}

	
}
