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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.CreditoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.CreditoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.CreditoService;




@RestController
@RequestMapping("/credito")
public class CreditoController {
	
	
	@Autowired
	CreditoService creditoCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public CreditoDTO adicionar(@RequestBody @Valid CreditoInput creitoInput) {
		return creditoCadastro.inserir(creitoInput);
	}
	
	@GetMapping
	public Page<CreditoDTO> listar(Pageable paginacao){
		return creditoCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public CreditoDTO Buscar(@PathVariable Long id) {
		return creditoCadastro.localzarentidade(id);
	}
	
	@PutMapping("/{id}")
	public CreditoDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid CreditoInput creitoInput) {
		
		return creditoCadastro.atualizar(id, creitoInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		creditoCadastro.excluir(id);
	}
	


}
