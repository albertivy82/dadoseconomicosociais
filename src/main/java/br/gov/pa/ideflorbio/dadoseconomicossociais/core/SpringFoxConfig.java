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
				.tags(new Tag("Localidade", "gerencia as localidades"));
		
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
