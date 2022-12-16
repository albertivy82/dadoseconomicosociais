package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.sql.Date;
import java.util.List;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Escolaridade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.EstadoCivil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Perfil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Sexo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.Getter;

@Getter
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
	
	private List<DoencaDTO> doenca;
	
	private EntrevistadoIdNomeDTO entrevistado;

}
