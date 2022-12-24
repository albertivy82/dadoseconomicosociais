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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ServicosBasicosDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ServicosBasicosInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.ServicosBasicosService;




@RestController
@RequestMapping("/servicos-basicos")
public class ServicosBasicosController {
	
	
	@Autowired
	ServicosBasicosService servicosBasicosCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ServicosBasicosDTO adicionar(@RequestBody @Valid ServicosBasicosInput sevicosBasicosInput) {
		return servicosBasicosCadastro.inserir(sevicosBasicosInput);
	}
	
	@GetMapping
	public Page<ServicosBasicosDTO> listar(Pageable paginacao){
		return servicosBasicosCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public ServicosBasicosDTO Buscar(@PathVariable Long id) {
		return servicosBasicosCadastro.localizarEntidade(id);
	}
	
	@PutMapping("/{id}")
	public ServicosBasicosDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid ServicosBasicosInput sevicosBasicosInput) {
		
		return servicosBasicosCadastro.atualizar(id, sevicosBasicosInput);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		servicosBasicosCadastro.excluir(id);
	}
	


}
