package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.AtividadesProdutivas;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtividadedProdutivaInput {
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private AtividadesProdutivas atividade;
	
	@NotNull
	private int pessoasEnvolvidas;
	
	@NotNull
	@PositiveOrZero(message = "informe um valor válido")
	private BigDecimal faturamentoAtividadeMesTotal;
	
	private ResidenciaIdInput residencia;

}
