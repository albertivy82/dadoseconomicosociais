package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.ServicoPublicos;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.atendimentoSaude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ServicosBasicos implements Serializable{

		
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private atendimentoSaude tipoAtendimento;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private ServicoPublicos servicosDeficitarios;
		
	
	@NotNull
	@OneToOne
	@JoinColumn(name="residencia")
	private Residencia residencia;
	
}
