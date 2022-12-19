package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrevistadorInput {
	
	@NotBlank
	private String Nome;

}
