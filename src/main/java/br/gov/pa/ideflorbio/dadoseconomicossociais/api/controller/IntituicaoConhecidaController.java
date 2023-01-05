package br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.InstituicoesConhecidasDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.InstituicaoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.InstituicaoConhecida;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.InstituicoesService;
import io.swagger.annotations.Api;



@Api(tags = "Instituições Conhecidas")
@RestController
@RequestMapping("/intituicoes-conhecidas")
public class IntituicaoConhecidaController {
	
	
	@Autowired
	InstituicoesService instituicoesCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public InstituicoesConhecidasDTO adicionar(@RequestBody @Valid InstituicaoInput instituicaoInput) {
		
		try {
			InstituicaoConhecida instituicao = mapper.map(instituicaoInput, InstituicaoConhecida.class);
			return mapper.map(instituicoesCadastro.inserir(instituicao), InstituicoesConhecidasDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public InstituicoesConhecidasDTO atualizar(@PathVariable Long id,
			@RequestBody @Valid InstituicaoInput instituicaoInput) {
		
		try {
			InstituicaoConhecida instituicao =  instituicoesCadastro.buscarEntidade(id);
			 instituicao.setResidencia(new Residencia());
			 mapper.map(instituicaoInput, instituicao);
			return mapper.map(instituicoesCadastro.inserir(instituicao), InstituicoesConhecidasDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	

	
	@GetMapping
	public List<InstituicoesConhecidasDTO> listar(){
		return instituicoesCadastro
				.listarTodos().stream().map(t->mapper.map(t, InstituicoesConhecidasDTO.class)).toList();

	}
	
	@GetMapping("/{id}")
	public InstituicoesConhecidasDTO Buscar(@PathVariable Long id) {
		return instituicoesCadastro.localizarEntidade(id);
	}
	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		instituicoesCadastro.excluir(id);
	}
	


}
