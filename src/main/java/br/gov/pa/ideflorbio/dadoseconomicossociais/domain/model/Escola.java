package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Escola implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	
	@Enumerated(EnumType.STRING)
	private SimNao merenda;
	
	@Enumerated(EnumType.STRING)
	private SimNao transporte;
	
	@Enumerated(EnumType.STRING)
	private SimNao educacaoAmbiental;
	
	@ManyToOne
	@JoinColumn(name="localidade")
	private Localidade localidade;

}
