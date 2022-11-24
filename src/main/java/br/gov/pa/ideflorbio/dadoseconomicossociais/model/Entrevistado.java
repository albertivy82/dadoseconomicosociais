package br.gov.pa.ideflorbio.dadoseconomicossociais.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrevistado extends Morador implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String alimentacao;
	@NotBlank
	private String compras;
	@NotBlank
	private String tipoAtendimento;
	@NotBlank
	private String servPublico;
	
	@OneToMany(mappedBy = "entrevistados")
	private List<Violencia> violenciaSofrida;
	
	@OneToMany(mappedBy = "entrevistados")
	private List<InstituicaoConhecida> instituicaoConhecida;
	
	@OneToMany(mappedBy = "entrevistados")
	private List<atividadeEconomica> atividadeEconomica;
	
	@OneToMany(mappedBy = "entrevistados")
	private List<Credito> cerdito;
	
	@OneToOne
	@JoinColumn(name = "morador")
	private  Morador morador;
	
	

}
