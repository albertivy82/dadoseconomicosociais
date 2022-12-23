package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;


import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResidenciaToLocaliadadeDTO {
	
	private Long id;
	
	private String latitude;
	
	private String longitude;
	
	private String imovel;
	
	private List<AtividadeProdutivaDTO> atividadeProdutiva;
	
	private List<RendaOutrasFontesDTO> rendaOutrasFontes;
	
	private List<CreditoDTO> credito;
		
	private EntrevistadoIdNomeDTO entrevistado;
	
}
