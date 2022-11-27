package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Escolaridade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.EstadoCivil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Sexo;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Morador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	private Date dataNascimento;
	
	private int idade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;
	@NotNull
	private String ondeEstuda;
	@NotNull
	private boolean trabalho;
	@NotBlank
	private String religiao;
	
	
	@ManyToMany
	@JoinTable(
	        name="morador_doenca",
	        joinColumns=
	            {@JoinColumn(name="morador")},
	        inverseJoinColumns=
	            {@JoinColumn(name="donca")}
	    )
	private List<Doenca> doenca;
	
	
	@OneToOne(mappedBy ="morador")
	private Entrevistado entrevitado;;

}
