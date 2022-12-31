package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.ServicoPublicos;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.atendimentoSaude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicosBasicosInput {
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private atendimentoSaude tipoAtendimento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ServicoPublicos servicosDeficitarios;
	
	@NotNull
	private ResidenciaIdInput residencia;
	
	

}
