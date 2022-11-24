package br.gov.pa.ideflorbio.dadoseconomicossociais.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Morador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private Date dataNascimento;
	private String sexo;
	private String estadoCivil;
	private String escolaridade;
	private String ondeEstuda;
	private boolean trabalho;
	private String religiao;
	
	private List<Doenca> doenca;
	
	
	@OneToOne(mappedBy ="morador")
	private Entrevistado entrevitado;;

}
