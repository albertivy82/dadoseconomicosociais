package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditoInput {
	
	@NotBlank 
	private String nome;
	
	@NotNull
	@PositiveOrZero(message = "informe um valor válido")
	private BigDecimal valor;
	
	@NotNull
	private ResidenciaIdInput residencia;

}
