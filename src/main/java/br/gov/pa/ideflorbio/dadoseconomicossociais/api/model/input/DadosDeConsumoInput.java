package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Alimentacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosDeConsumoInput {

	@NotNull
	@Enumerated(EnumType.STRING)
	private Alimentacao alimentacaoPrincipal;
	@NotBlank
	private String LocalDeCompras;
	@NotNull
	private ResidenciaIdInput residencia;
		
}
