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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class MoradiaConexao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	private Long id;

	@NotNull
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dataChegada;
	
	@Enumerated(EnumType.STRING)
	private SimNao pretendeMudar;
	
	private String relacaoArea;
	
	private String relacaoVizinhos;
	
	@OneToOne
	@JoinColumn(name="entrevistado")
	private Entrevistado entrevistado;
	
	@OneToOne
	@JoinColumn(name="residencia")
	private Residencia residencia;
	
	
	

}
