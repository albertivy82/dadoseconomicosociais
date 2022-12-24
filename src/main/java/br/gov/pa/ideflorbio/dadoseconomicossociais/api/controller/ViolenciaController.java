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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ViolenciasSofridasDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ViolenciaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.ViolenciaService;




@RestController
@RequestMapping("/violencia")
public class ViolenciaController {
	
	
	@Autowired
	ViolenciaService violenciaCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ViolenciasSofridasDTO adicionar(@RequestBody @Valid ViolenciaInput violenciaInput) {
		return violenciaCadastro.inserir(violenciaInput);
	}
	
	@GetMapping
	public Page<ViolenciasSofridasDTO> listar(Pageable paginacao){
		return violenciaCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public ViolenciasSofridasDTO Buscar(@PathVariable Long id) {
		return violenciaCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public ViolenciasSofridasDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid ViolenciaInput violenciaInput) {
		
		return violenciaCadastro.atualizar(id, violenciaInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		violenciaCadastro.excluir(id);
	}
	


}
