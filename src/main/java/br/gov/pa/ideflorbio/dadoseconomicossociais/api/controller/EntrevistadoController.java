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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EntrevistadoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.EntrevistadoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.EntrevistadoService;



@RestController
@RequestMapping("/entrevistado")
public class EntrevistadoController {
	
	
	@Autowired
	EntrevistadoService entrevistadosCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public EntrevistadoDTO adicionar(@RequestBody @Valid EntrevistadoInput entrevistadoInput) {
		return entrevistadosCadastro.inserir(entrevistadoInput);
	}
	
	@GetMapping
	public Page<EntrevistadoDTO> listar(Pageable paginacao){
		return entrevistadosCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public EntrevistadoDTO Buscar(@PathVariable Long id) {
		return entrevistadosCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public EntrevistadoDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid EntrevistadoInput entrevistadoInput) {
		
		return entrevistadosCadastro.atualizar(id, entrevistadoInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		entrevistadosCadastro.excluir(id);
	}
	


}
