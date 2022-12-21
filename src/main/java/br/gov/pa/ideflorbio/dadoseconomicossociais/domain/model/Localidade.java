package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Municipio;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Localidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Municipio municipio;
	
	@NotBlank
	private String latitude;
	
	@NotBlank
	private String longitude;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao postoDeSaude;
	
	@JsonIgnore
	@OneToMany(mappedBy="localidade")
	private List<Escola> escolas;
	
	@JsonIgnore
	@OneToMany(mappedBy="localidade")
	private List<Residencia> residencia;
	
	@ManyToMany
	@JoinTable(
	        name="localidade_entrevistador",
	        joinColumns=
	            @JoinColumn(name="localidade"),
	        inverseJoinColumns=
	            @JoinColumn(name="entrevistador")
	    )
	private Set<Entrevistador> entrevistador = new HashSet<>();
	

}
