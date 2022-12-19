package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscolaReciboDTO {
	
	private String nome;
	
	private String merenda;
	
	private String transporte;
	
	private String educacaoAmbiental;
	
	private LocalidadeAssociacaoDTO localidade;

}
