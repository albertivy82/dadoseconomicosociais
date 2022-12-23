package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import java.sql.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Escolaridade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.EstadoCivil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Perfil;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Sexo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MoradorInput {
	
	@NotNull
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dataNascimento;
	
	@NotNull
	private int idade;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Escolaridade escolaridade;
	
	private String ondeEstuda;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao trabalho;
	
	@NotBlank
	private String religiao;
	
	private ResidenciaIdInput residencia;


}
