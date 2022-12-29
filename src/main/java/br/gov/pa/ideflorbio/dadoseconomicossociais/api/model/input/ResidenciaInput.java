package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import java.sql.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeAssociacaoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Imovel;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResidenciaInput {
	
    private String rua;
		
	private String numero;
	
	private String bairro;
	
	@NotBlank
	private String referencial;
	
	@NotBlank
	private String latitude;
	
	@NotBlank
	private String longitude;
	
	
	@Enumerated(EnumType.STRING)
	private Imovel imovel;
	
	
	@NotNull
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dataChegada;
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao pretendeMudar;
	

	private String MotivoVontadeMudança;
	
	@NotBlank
	private String relacaoArea;
	
	@NotBlank
	private String relacaoVizinhos;
	
	
	@NotNull
	private LocalidadeAssociacaoDTO localidade;
	

}
