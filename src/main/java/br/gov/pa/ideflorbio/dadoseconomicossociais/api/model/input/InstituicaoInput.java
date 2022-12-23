package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InstituicaoInput {
	
	@NotBlank
	private String Nome;
	
	@NotBlank
	private String atividades;
	
	@NotNull
	private ResidenciaIdInput residencia;

}
