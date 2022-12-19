package br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller;



import javax.validation.Valid;

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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.LocalidadeInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.LocalidadeService;



@RestController
@RequestMapping("/localidade")
public class LocalidadeController {
	
	@Autowired
	LocalidadeService localidadeCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public LocalidadeReciboDTO adicionar(@RequestBody @Valid LocalidadeInput localidadeInput) {
		return localidadeCadastro.inserir(localidadeInput);
	}
	
	@GetMapping
	public Page<LocalidadeDTO> listar(Pageable paginacao){
		return localidadeCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public LocalidadeDTO Buscar(@PathVariable Long id) {
		return localidadeCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public LocalidadeReciboDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid LocalidadeInput localidadeInput) {
		
		return localidadeCadastro.atualizar(id, localidadeInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		localidadeCadastro.excluir(id);
	}

}
