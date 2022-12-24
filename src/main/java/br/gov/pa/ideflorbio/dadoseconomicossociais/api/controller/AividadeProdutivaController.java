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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.AtividadeProdutivaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.AtividadeProdutivaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.AtividadesProdutivasService;




@RestController
@RequestMapping("/atividade-produtiva")
public class AividadeProdutivaController {
	
	
	@Autowired
	AtividadesProdutivasService atividadesProdutivasCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public AtividadeProdutivaDTO adicionar(@RequestBody @Valid AtividadeProdutivaInput atividadesInput) {
		return atividadesProdutivasCadastro.inserir(atividadesInput);
	}
	
	@GetMapping
	public Page<AtividadeProdutivaDTO> listar(Pageable paginacao){
		return atividadesProdutivasCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public AtividadeProdutivaDTO Buscar(@PathVariable Long id) {
		return atividadesProdutivasCadastro.localzarentidade(id);
	}
	
	@PutMapping("/{id}")
	public AtividadeProdutivaDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid AtividadeProdutivaInput atividadesInput) {
		
		return atividadesProdutivasCadastro.atualizar(id, atividadesInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		atividadesProdutivasCadastro.excluir(id);
	}
	


}
