package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditoDTO {

	private String nome;
	
	private BigDecimal valor;
	
}
