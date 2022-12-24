package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IndicadoDTO {
	
	private String nome;
	
	private String perfil;
	
	private String telefone;
	
	private EntrevistadoIdNomeDTO entrevistado;

}
