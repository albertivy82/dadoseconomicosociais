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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.LocalidadeInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.LocalidadeService;
import io.swagger.annotations.Api;

@Api(tags = "Localidade")
@RestController
@RequestMapping("/localidade")
public class LocalidadeController {
	
	@Autowired
	LocalidadeService localidadeCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public LocalidadeDTO adicionar(@RequestBody @Valid LocalidadeInput localidadeInput) {
		return localidadeCadastro.inserir(localidadeInput);
	}
	
	@GetMapping
	public List<LocalidadeDTO> listar(){
		return localidadeCadastro
				.listarTodos().stream().map(t->mapper.map(t, LocalidadeDTO.class)).toList();
	}
	
	@GetMapping("/{id}")
	public LocalidadeDTO Buscar(@PathVariable Long id) {
		return localidadeCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public LocalidadeDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid LocalidadeInput localidadeInput) {
		
		return localidadeCadastro.atualizar(id, localidadeInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		localidadeCadastro.excluir(id);
	}

}
