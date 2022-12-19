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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EntrevistadorDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.EntrevistadorInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.EntrevistadorService;

@RestController
@RequestMapping("/entrevistadores")
public class EntrevistadorController {
	
	
	@Autowired
	EntrevistadorService entrevistadorCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public EntrevistadorDTO adicionar(@RequestBody @Valid EntrevistadorInput entrevistadorInput) {
		return entrevistadorCadastro.inserir(entrevistadorInput);
	}
	
	@GetMapping
	public Page<EntrevistadorDTO> listar(Pageable paginacao){
		return entrevistadorCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/buscaporid/{id}")
	public EntrevistadorDTO Buscar(@PathVariable Long id) {
		return entrevistadorCadastro.buscaPorId(id);
	}
	
	@GetMapping("/buscapornome/{nome}")
	public EntrevistadorDTO Buscar(@PathVariable String nome) {
		return entrevistadorCadastro.buscarPorNome(nome);
	}
	
	@PutMapping("/{id}")
	public EntrevistadorDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid EntrevistadorInput entrevistadorInput) {
		return entrevistadorCadastro.atualizar(id, entrevistadorInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		entrevistadorCadastro.apagar(id);
	}

	

}
