package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntrevistadoDTO {

	
	private Long id;
	
	private String nome;
	
	private String alimentacao;
	
	private String compras;
	
	private String tipoAtendimento;
	
	private String servPublico;
	
	private List<ViolenciasSofridasDTO> violenciaSofrida;
	
	private List<InstituicoesConhecidasDTO> instituicaoConhecida;
	
	private List<AtividadeProdutivaDTO> atividadeProdutiva;
	
	private List<CreditoDTO> credito;
	
	private List<MoradoresVinculadosDTO> morador;
	
	
	
}
