package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrevistadoInput {

	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String apelido;
	
	@NotBlank
	private String naturalidade;
	
	@NotNull
	private ResidenciaIdInput residencia;
	

}
