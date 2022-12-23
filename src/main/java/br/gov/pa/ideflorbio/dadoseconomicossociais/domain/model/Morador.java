package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Escolaridade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.EstadoCivil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Perfil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Sexo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import java.util.HashSet;
import java.util.Set;

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
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dataNascimento;
	
	@NotNull
	private int idade;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;
	
	

	private String ondeEstuda;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao trabalho;
	
	@NotBlank
	private String religiao;
	
	
	@ManyToMany
	@JoinTable(
	        name="morador_doenca",
	        joinColumns=
	            @JoinColumn(name="morador"),
	        inverseJoinColumns=
	            @JoinColumn(name="doenca")
	    )
	private Set<Doenca> doenca = new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name="residencia")
	private Residencia residencia;

}
