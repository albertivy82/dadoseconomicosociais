package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ResidenciaIdInput;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntrevistadoDTO {

	
	private Long id;
	
	private String nome;
	
	private String apelido;
	
	private String naturalidade;
	
	private ResidenciaIdInput residencia;
	
}
