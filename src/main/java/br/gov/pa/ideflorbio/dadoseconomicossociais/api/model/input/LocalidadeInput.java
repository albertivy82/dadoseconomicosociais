package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Municipio;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LocalidadeInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Municipio municipio;
	
	@NotBlank
	private String latitude;
	
	@NotBlank
	private String longitude;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao postoDeSaude;
	
}
