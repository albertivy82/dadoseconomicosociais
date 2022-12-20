package br.gov.pa.ideflorbio.dadoseconomicossociais.api.exceptionhandler;


public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encotrado", "Recurso não Encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso-por-outra-entidade", "Entidade em Uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	TIPO_DE_MIDIA_INVALIDO("/midia-invalida", "api não suporta o tipo de mídia requisitado"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro de Inválido"),
	ERRO_DO_SISTEMA("/erro-do-sistema", "Erro do Siistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados Inválidos"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreesível");
	
	
	private String title;
	private String uri;
	
    ProblemType(String path, String title){
    	this.uri = "https://ideflorbio.pa.gov.br"+path;
    	this.title = title;
    }
    
	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}

}
