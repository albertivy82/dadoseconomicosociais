package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.sql.Date;
import java.util.List;

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

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Doenca;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistado;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Escolaridade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.EstadoCivil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Perfil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Sexo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.EqualsAndHashCode;

public class MoradorDTO {
	
	private Long id;
	
	private Date dataNascimento;
	
	private int idade;
	
	private Perfil perfil;
	
	private Sexo sexo;
	
	private EstadoCivil estadoCivil;
	
	private Escolaridade escolaridade;
	
	private SimNao ondeEstuda;
	
	private boolean trabalho;
	
	private String religiao;
	
	private List<DoencaNomeDTO> doenca;
	
	private EntrevistadoDTO entrevistado;

}
