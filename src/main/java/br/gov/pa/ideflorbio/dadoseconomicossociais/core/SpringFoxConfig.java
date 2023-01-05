package br.gov.pa.ideflorbio.dadoseconomicossociais.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	
	@Bean
	public Docket apiDocket() {
		
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage(
						"br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller"))
				.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Localidade", "gerencia as localidades"),
						new Tag("Atividade Produtiva", "gerencia as atividades produtivas de cada residência"),
						new Tag("Créditos", "gerencia créditos obtidos em cada residência"),
						new Tag("Dados de Consumo", "gerencia os dados de consumo de cada residencia"),
						new Tag("Doença", "gerencia o registro de doenças nas residências"),
						new Tag("Entrevistado", "gerencia os dados dos entrevistados"),
						new Tag("Indicado Para Consultas Públicas", "gerencia quem o entrevistado indica para a consulta pública"),
						new Tag("Instituições Conhecidas", "gerencia as instituições atuantes na região por residência"),
						new Tag("LocalidadeXEntrevistador", "gerencia o vínculo entre entrevistador e localidades"),
						new Tag("Morador", "os dados sobre os moradores de cada residência"),
						new Tag("Fontes de Renda", "gerencia as fontes de renda de cada residência"),
						new Tag("Residência", "gerencia as residêsncias de cada localidade"),
						new Tag("Serviços Básicos", "gerencia informações os serviços básicos por residência"),
						new Tag("Violência", "gerencia as ocorrências de violência por residência"));
		
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("IDEFLOR-Bio")
				.description("Levantamento de dados soiais")
				.version("1")
				.contact(new Contact("NTI/Ideflor-bio", "https://www.ideflobio.pa.gov.br", "nti@ideflorbio.pa.gov.br"))
				.build();
	}
	

}
