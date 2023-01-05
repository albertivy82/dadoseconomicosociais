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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EscolaReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.EscolaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.LocalidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Escola;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.EscolaService;
import io.swagger.annotations.Api;



@Api(tags = "Escola")
@RestController
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	EscolaService escolaCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public EscolaReciboDTO adicionar(@RequestBody @Valid EscolaInput escolaInput) {
		try {
			
			Escola escola = mapper.map(escolaInput, Escola.class);
			return mapper.map(escolaCadastro.inserir(escola), EscolaReciboDTO.class);
			
			}catch (LocalidadeNaoEncontradaException e){
				throw new 	EntidadeNaoEncontradaException(e.getMessage());			
			}
	}
	
	@PutMapping("/{id}")
	public EscolaReciboDTO atualizar(@PathVariable Long id, 
		@RequestBody @Valid EscolaInput escolaInput) {
		try {
		Escola escolaAtual = escolaCadastro.buscarEntidade(id);
		escolaAtual.setLocalidade(new Localidade());
		mapper.map(escolaInput, escolaAtual);
		return mapper.map(escolaCadastro.inserir(escolaAtual), EscolaReciboDTO.class);
		}catch (LocalidadeNaoEncontradaException e){
			throw new 	EntidadeNaoEncontradaException(e.getMessage());			
		}
	}
	
	@GetMapping
	public List<EscolaReciboDTO> listar(){
		return escolaCadastro
				.listarTodos().stream().map(t->mapper.map(t, EscolaReciboDTO.class)).toList();

	}
	
	@GetMapping("/{id}")
	public EscolaReciboDTO Buscar(@PathVariable Long id) {
		return escolaCadastro.localizarEntidade(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		escolaCadastro.excluir(id);
	}

}
