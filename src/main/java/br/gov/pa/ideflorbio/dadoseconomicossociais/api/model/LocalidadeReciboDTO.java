package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadeReciboDTO {
	
    
	private Long id;
	
	private String nome;
		
	private String municipio;
	
	private String latitude;
	
	private String longitude;
	
	private String postoDeSaude;
	
}
