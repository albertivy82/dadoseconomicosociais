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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.InstituicoesConhecidasDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.InstituicaoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.InstituicoesService;




@RestController
@RequestMapping("/intituicoes-conhecidas")
public class IntituicaoConhecidaController {
	
	
	@Autowired
	InstituicoesService instituicoesCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public InstituicoesConhecidasDTO adicionar(@RequestBody @Valid InstituicaoInput instituicaoInput) {
		return instituicoesCadastro.inserir(instituicaoInput);
	}
	
	@GetMapping
	public Page<InstituicoesConhecidasDTO> listar(Pageable paginacao){
		return instituicoesCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public InstituicoesConhecidasDTO Buscar(@PathVariable Long id) {
		return instituicoesCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public InstituicoesConhecidasDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid InstituicaoInput instituicaoInput) {
		
		return instituicoesCadastro.atualizar(id, instituicaoInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		instituicoesCadastro.excluir(id);
	}
	


}
