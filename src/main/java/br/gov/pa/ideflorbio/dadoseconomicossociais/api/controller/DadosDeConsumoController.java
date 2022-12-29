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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.DadosDeConsumoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.DadosDeConsumoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.DadosDeConsumoService;




@RestController
@RequestMapping("/dados-de-consumo")
public class DadosDeConsumoController {
	
	
	@Autowired
	DadosDeConsumoService dadosDeConsumoCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public DadosDeConsumoDTO adicionar(@RequestBody @Valid DadosDeConsumoInput consumoInput) {
		return dadosDeConsumoCadastro.inserir(consumoInput);
	}
	
	@GetMapping
	public Page<DadosDeConsumoDTO> listar(Pageable paginacao){
		return dadosDeConsumoCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public DadosDeConsumoDTO Buscar(@PathVariable Long id) {
		return dadosDeConsumoCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public DadosDeConsumoDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid DadosDeConsumoInput consumoInput) {
		
		return dadosDeConsumoCadastro.atualizar(id, consumoInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		dadosDeConsumoCadastro.excluir(id);
	}
	


}
