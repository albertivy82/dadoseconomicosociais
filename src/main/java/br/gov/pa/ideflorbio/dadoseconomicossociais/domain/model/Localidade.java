package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.util.List;
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
	private String referencial;
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Municipio municipio;
	@NotBlank
	private String latitude;
	@NotBlank
	private String longitude;
	@NotBlank
	@Enumerated(EnumType.STRING)
	private SimNao postoDeSaude;
	
	@OneToMany(mappedBy="localidade")
	private List<Escola> escolas;
	
	@ManyToMany
	@JoinTable(
	        name="localidade_entrevistador",
	        joinColumns=
	            {@JoinColumn(name="localidade")},
	        inverseJoinColumns=
	            {@JoinColumn(name="entrevistador")}
	    )
	private List<Entrevistador> entrevistador;
	

}
