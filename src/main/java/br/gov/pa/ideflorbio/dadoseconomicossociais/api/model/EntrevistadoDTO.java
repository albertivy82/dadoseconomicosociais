package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.util.List;

import lombok.Getter;


@Getter
public class EntrevistadoDTO {

	
	private Long id;
	
	private String nome;
	
	private String alimentacao;
	
	private String compras;
	
	private String tipoAtendimento;
	
	private String servPublico;
	
	private List<ViolenciasSofridasDTO> violenciaSofrida;
	
	private List<InstituicoesConhecidasDTO> instituicaoConhecida;
	
	private List<AtividadesEconomicasDTO> atividadeEconomica;
	
	private List<CreditoObtidosDTO> credito;
	
	private List<MoradoresVinculadosDTO> morador;
	
	//private MoradiaConexao moradia;
	
	
}
