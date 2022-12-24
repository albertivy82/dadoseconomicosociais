package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.PerfilDoIndicado;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IndicadoInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PerfilDoIndicado perfil;
	
	@NotBlank
	private String telefone;
	
	@NotNull
	private EntrevistadoIdInput entrevistado;

}
