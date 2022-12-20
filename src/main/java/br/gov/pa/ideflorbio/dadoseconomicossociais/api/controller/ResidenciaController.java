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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaListaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ResidenciaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.ResidenciaService;




@RestController
@RequestMapping("/residencias")
public class ResidenciaController {
	
	@Autowired
	ResidenciaService residenciaCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ResidenciaReciboDTO adicionar(@RequestBody @Valid ResidenciaInput residenciaInput) {
		return residenciaCadastro.inserir(residenciaInput);
	}
	
	@GetMapping
	public Page<ResidenciaListaDTO> listar(Pageable paginacao){
		return residenciaCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResidenciaDTO Buscar(@PathVariable Long id) {
		return residenciaCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public ResidenciaReciboDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid ResidenciaInput residenciaInput) {
		
		return residenciaCadastro.atualizar(id, residenciaInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		residenciaCadastro.excluir(id);
	}

}
