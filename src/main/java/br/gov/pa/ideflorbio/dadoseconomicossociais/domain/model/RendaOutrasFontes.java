package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.FontesRenda;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RendaOutrasFontes implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	
	@Enumerated(EnumType.STRING)
	private FontesRenda fonte;
	
	
	private int beneficiarios;
	
	
	@PositiveOrZero(message = "informe um valor válido")
	private BigDecimal rendaMesTotal;
	
	
	@ManyToOne
	@JoinColumn(name="residencia")
	private Residencia residencia;
}
