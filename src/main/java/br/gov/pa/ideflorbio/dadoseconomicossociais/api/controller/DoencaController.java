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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.DoencaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.DoencaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Doenca;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.DoencaService;


@RestController
@RequestMapping("/doencas")
public class DoencaController {
	
	
	@Autowired
	DoencaService doencasCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public DoencaDTO adicionar(@RequestBody @Valid DoencaInput doencaInput) {
		Doenca doenca = mapper.map(doencaInput, Doenca.class);
		return mapper.map(doencasCadastro.inserir(doenca), DoencaDTO.class);
	}
	
	@PutMapping("/{id}")
	public DoencaDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid DoencaInput doencaInput) {
		
		Doenca doenca = doencasCadastro.buscarEntidade(id);
		mapper.map(doencaInput, doenca);
		
		return mapper.map(doencasCadastro.inserir(doenca), DoencaDTO.class);
		
	}
	
	@GetMapping
	public Page<DoencaDTO> listar(Pageable paginacao){
		return doencasCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public DoencaDTO Buscar(@PathVariable Long id) {
		return doencasCadastro.localzarentidade(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		doencasCadastro.apagar(id);
	}
	
}	
