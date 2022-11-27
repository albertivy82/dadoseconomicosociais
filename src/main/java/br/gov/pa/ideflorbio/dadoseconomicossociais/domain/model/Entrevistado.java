package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrevistado implements Serializable{
	
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "entrevistado")
	private List<Violencia> violenciaSofrida;
	
	@OneToMany(mappedBy = "entrevistado")
	private List<InstituicaoConhecida> instituicaoConhecida;
	
	@OneToMany(mappedBy = "entrevistado")
	private List<AtividadeEconomica> atividadeEconomica;
	
	@OneToMany(mappedBy = "entrevistado")
	private List<Credito> cerdito;
	
	@OneToOne
	@JoinColumn(name = "morador")
	private  Morador morador;
	
	

}
