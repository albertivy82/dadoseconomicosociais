package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscolaInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao merenda;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao transporte;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao educacaoAmbiental;
	
	@NotNull
	private LocalidadeIdInput localidade;

}
