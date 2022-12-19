package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosDeConsumoInput {

	@NotBlank
	private String alimentacaoPrincipal;
	@NotBlank
	private String LocalDeCompras;
	@NotNull
	private ResidenciaIdInput residencia;
		
}
