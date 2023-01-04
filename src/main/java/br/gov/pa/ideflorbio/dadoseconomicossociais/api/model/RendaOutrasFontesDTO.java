package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RendaOutrasFontesDTO {
	
	private Long id;
	
	private String fonte;
	
	private int beneficiarios;
	
	private BigDecimal rendaMesTotal;

}
