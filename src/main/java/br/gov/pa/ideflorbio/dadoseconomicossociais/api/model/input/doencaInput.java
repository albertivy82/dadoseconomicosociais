package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Molestias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class doencaInput {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Molestias DoencaNome;

}
