package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.FontesRenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RendasOutrasFontesInput {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private FontesRenda fonte;
	
	@NotNull
	private int beneficiarios;
	
	@NotNull
	@PositiveOrZero(message = "informe um valor válido")
	private BigDecimal rendaMesTotal;
	
	@NotNull
	private ResidenciaIdInput residencia;

}
