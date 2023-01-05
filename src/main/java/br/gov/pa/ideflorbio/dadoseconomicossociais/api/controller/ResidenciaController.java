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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ResidenciaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.LocalidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.ResidenciaService;
import io.swagger.annotations.Api;



@Api(tags = "Residência")
@RestController
@RequestMapping("/residencias")
public class ResidenciaController {
	
	@Autowired
	ResidenciaService residenciaCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ResidenciaDTO adicionar(@RequestBody @Valid ResidenciaInput residenciaInput) {
		try {
			
		Residencia residencia = mapper.map(residenciaInput, Residencia.class);
		return mapper.map(residenciaCadastro.inserir(residencia), ResidenciaDTO.class);
		
		}catch (LocalidadeNaoEncontradaException e){
			throw new 	EntidadeNaoEncontradaException(e.getMessage());			
		}
	}
	
	@PutMapping("/{id}")
	public ResidenciaDTO atualizar(@PathVariable Long id, 
		@RequestBody @Valid ResidenciaInput residenciaInput) {
		try {
		Residencia residenciaAtual = residenciaCadastro.buscarEntidade(id);
		residenciaAtual.setLocalidade(new Localidade());
		mapper.map(residenciaInput, residenciaAtual);
		return mapper.map(residenciaCadastro.inserir(residenciaAtual), ResidenciaDTO.class);
		}catch (LocalidadeNaoEncontradaException e){
			throw new 	EntidadeNaoEncontradaException(e.getMessage());			
		}
	}

	@GetMapping
	public List<ResidenciaDTO> listar(){
		return residenciaCadastro
				.listarTodos().stream().map(t->mapper.map(t, ResidenciaDTO.class)).toList();
	}
	
	@GetMapping("/{id}")
	public ResidenciaDTO Buscar(@PathVariable Long id) {
		return residenciaCadastro.localizarEntidade(id);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		residenciaCadastro.excluir(id);
	}

}
