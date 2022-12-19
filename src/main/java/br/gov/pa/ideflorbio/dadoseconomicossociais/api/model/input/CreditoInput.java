package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


public class CreditoInput {
	
	@NotBlank 
	private String nome;
	
	@NotBlank
	@PositiveOrZero(message = "informe um valor válido")
	private BigDecimal valor;
	
	@NotNull
	private ResidenciaIdInput residencia;

}
