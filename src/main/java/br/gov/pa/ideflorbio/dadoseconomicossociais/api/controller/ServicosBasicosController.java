package br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ServicosBasicosDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ServicosBasicosInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.ServicosBasicos;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.ServicosBasicosService;




@RestController
@RequestMapping("/servicos-basicos")
public class ServicosBasicosController {
	
	
	@Autowired
	ServicosBasicosService servicosBasicosCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ServicosBasicosDTO adicionar(@RequestBody @Valid ServicosBasicosInput sevicosBasicosInput) {
		try {
		ServicosBasicos servicosBasicos = mapper.map(sevicosBasicosInput, ServicosBasicos.class);
		return mapper.map(servicosBasicosCadastro.inserir(servicosBasicos), ServicosBasicosDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ServicosBasicosDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid ServicosBasicosInput servicosBasicosInput) {
		try {
			ServicosBasicos servicoAtual = servicosBasicosCadastro.buscarEntidade(id);
			servicoAtual.setResidencia(new Residencia());
			mapper.map(servicosBasicosInput, servicoAtual);
		return mapper.map(servicosBasicosCadastro.inserir(servicoAtual), ServicosBasicosDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	@GetMapping
	public Page<ServicosBasicosDTO> listar(Pageable paginacao){
		return servicosBasicosCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public ServicosBasicosDTO Buscar(@PathVariable Long id) {
		return servicosBasicosCadastro.localizarEntidade(id);
	}
	
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		servicosBasicosCadastro.excluir(id);
	}
	


}
