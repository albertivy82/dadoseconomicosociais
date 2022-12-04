package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Residencia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String localidade;
	
	@NotBlank
	private String referencial;
	
	@OneToOne(mappedBy = "residencia")
	private MoradiaConexao moradia;

}
