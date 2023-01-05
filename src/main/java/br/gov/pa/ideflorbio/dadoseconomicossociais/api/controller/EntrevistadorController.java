package br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistador;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.EntrevistadorService;
import io.swagger.annotations.Api;

@Api(tags = "Entervistador")
@RestController
@RequestMapping("/entrevistadores")
public class EntrevistadorController {
	
	
	@Autowired
	EntrevistadorService entrevistadorCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public EntrevistadorDTO adicionar(@RequestBody @Valid EntrevistadorInput entrevistadorInput) {
		
		Entrevistador entrevistador = mapper.map(entrevistadorInput, Entrevistador.class);
		return mapper.map(entrevistadorCadastro.inserir(entrevistador), EntrevistadorDTO.class);
		
	}
	
	@PutMapping("/{id}")
	public EntrevistadorDTO atualizar(@PathVariable Long id, 
		@RequestBody @Valid EntrevistadorInput entrevistadorInput) {

		Entrevistador entrevistadorAtual = entrevistadorCadastro.buscarEntidade(id);
		mapper.map(entrevistadorInput, entrevistadorAtual);
		return mapper.map(entrevistadorCadastro.inserir(entrevistadorAtual), EntrevistadorDTO.class);
		
	}

	
	@GetMapping
	public List<EntrevistadorDTO> listar(){
		return entrevistadorCadastro
				.listarTodos().stream().map(t->mapper.map(t, EntrevistadorDTO.class)).toList();
		
	}
	
	@GetMapping("/buscaporid/{id}")
	public EntrevistadorDTO Buscar(@PathVariable Long id) {
		return entrevistadorCadastro.buscaPorId(id);
	}
	
	@GetMapping("/buscapornome/{nome}")
	public EntrevistadorDTO Buscar(@PathVariable String nome) {
		return entrevistadorCadastro.buscarPorNome(nome);
	}
	
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		entrevistadorCadastro.apagar(id);
	}

	

}
