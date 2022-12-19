package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtividadeProdutivaDTO {
	
	private String atividade;
	
	private int pessoasEnvolvidas;
	
	private BigDecimal faturamentoAtividadeMesTotal;

}
