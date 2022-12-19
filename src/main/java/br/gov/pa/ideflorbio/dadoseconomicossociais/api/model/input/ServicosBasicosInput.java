package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.ServicoPublicos;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.atendimentoSaude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicosBasicosInput {
	
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private atendimentoSaude tipoAtendimento;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private ServicoPublicos servicosDeficitarios;
	
	private ResidenciaIdInput residencia;
	
	

}
