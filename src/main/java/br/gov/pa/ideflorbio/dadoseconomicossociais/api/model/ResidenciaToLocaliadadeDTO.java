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
	
	private List<AtividadeProdutivaResumoDTO> atividadeProdutiva;
	
	private List<RendaOutrasResumoFontesDTO> rendaOutrasFontes;
	
	private List<CreditoResumoDTO> credito;
		
	private EntrevistadoIdNomeDTO entrevistado;
	
}
