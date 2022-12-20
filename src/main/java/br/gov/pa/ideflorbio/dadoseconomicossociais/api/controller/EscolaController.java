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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EscolaReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.EscolaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.EscolaService;




@RestController
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	EscolaService escolaCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public EscolaReciboDTO adicionar(@RequestBody @Valid EscolaInput escolaInput) {
		return escolaCadastro.inserir(escolaInput);
	}
	
	@GetMapping
	public Page<EscolaReciboDTO> listar(Pageable paginacao){
		return escolaCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public EscolaReciboDTO Buscar(@PathVariable Long id) {
		return escolaCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public EscolaReciboDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid EscolaInput escolaInput) {
		
		return escolaCadastro.atualizar(id, escolaInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		escolaCadastro.excluir(id);
	}

}
