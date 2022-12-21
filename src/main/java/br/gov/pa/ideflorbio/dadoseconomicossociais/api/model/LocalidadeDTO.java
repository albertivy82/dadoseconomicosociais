package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadeDTO {
	
    
	private String nome;
		
	private String municipio;
	
	private String latitude;
	
	private String longitude;
	
	private List<ResidenciaToLocaliadadeDTO> residencia;
	
	private List<EscolaToLocalidadeDTO> escola;
	
}
