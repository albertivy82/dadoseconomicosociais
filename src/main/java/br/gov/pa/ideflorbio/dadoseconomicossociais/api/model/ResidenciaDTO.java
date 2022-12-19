package br.gov.pa.ideflorbio.dadoseconomicossociais.api.model;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidenciaDTO {
	
    private String rua;
	
	private String numero;
	
	private String bairro;
	
	private String referencial;
	
	private String latitude;
	
	private String longitude;
	
	private String imovel;
	
	private Date dataChegada;
	
	private String pretendeMudar;
	
	private String MotivoVontadeMudança;
	
	private String relacaoArea;
	
	private String relacaoVizinhos;
	
	private LocalidadeDTO localidade;
	
	private EntrevistadoNomeDTO entrevistado;
	
	private DadosDeConsumoDTO consumo;
	
	private ServicosBasicosDTO servicosBasicos;
	
	private List<ViolenciasSofridasDTO> violenciaSofrida;
	
	private List<InstituicoesConhecidasDTO> instituicaoConhecida;
	
	private List<AtividadeProdutivaDTO> atividadeProdutiva;
	
	private List<RendaOutrasFontesDTO> rendaOutrasFontes;
	
	private List<CreditoDTO> credito;
	
	private  List<MoradorDTO> morador;
	
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if(rua.equals(null)) {
			this.rua = "Rua não identificada";
		}
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if(numero.equals(null)) {
			this.numero = "S/N";
		}
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		
		if(bairro.equals(null)) {
			this.bairro = "bairro não identificado";
		}
		
		this.bairro = bairro;
	}
	

}
