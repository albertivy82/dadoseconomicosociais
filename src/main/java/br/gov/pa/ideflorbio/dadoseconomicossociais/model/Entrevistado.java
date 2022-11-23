package br.gov.pa.ideflorbio.dadoseconomicossociais.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.gov.pa.ideflorbio.dadoseconomicossociais.Violencia;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrevistado{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String alimentacao;
	private String compras;
	private String tipoAtendimento;
	private String servPuublico;
	
	private List<Violencia> violenciaSofrida;
	private List<InstituicaoConhecida> instituicaoConhecida;
	private List<atividadeEconomica> atividadeEconomica;
	private List<Credito> cerdito;
	
	

}
