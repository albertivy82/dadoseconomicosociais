package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.sql.Date;
import java.util.List;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ResidenciaIdInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoradorDTO {
	
	
	private Date dataNascimento;
	
	private int idade;
	
	private String perfil;
	
	private String sexo;
	
	private String estadoCivil;
	
	private String escolaridade;
	
	private String ondeEstuda;
	
	private String trabalho;
	
	private String religiao;
	
	private List<DoencaDTO> doenca;
	
	private ResidenciaIdInput residencia;

}
