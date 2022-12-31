package br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.AtividadeProdutiva;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.AtividadesProdutivasService;




@RestController
@RequestMapping("/atividade-produtiva")
public class AividadeProdutivaController {
	
	
	@Autowired
	AtividadesProdutivasService atividadesProdutivasCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public AtividadeProdutivaDTO adicionar(@RequestBody @Valid AtividadeProdutivaInput atividadesInput) {
		
		try {
			AtividadeProdutiva atividadesProdutiva = mapper.map(atividadesInput, AtividadeProdutiva.class);
			return mapper.map(atividadesProdutivasCadastro.inserir(atividadesProdutiva), AtividadeProdutivaDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public AtividadeProdutivaDTO atualizar(@PathVariable Long id,
			@RequestBody @Valid AtividadeProdutivaInput atividadesInput) {
		
		try {
			 AtividadeProdutiva atividadesProdutivas =  atividadesProdutivasCadastro.buscarEntidade(id);
			 atividadesProdutivas.setResidencia(new Residencia());
			 mapper.map(atividadesInput, atividadesProdutivas);
			return mapper.map(atividadesProdutivasCadastro.inserir(atividadesProdutivas), AtividadeProdutivaDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		

	}
	
	
	@GetMapping
	public Page<AtividadeProdutivaDTO> listar(Pageable paginacao){
		return atividadesProdutivasCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public AtividadeProdutivaDTO Buscar(@PathVariable Long id) {
		return atividadesProdutivasCadastro.localzarentidade(id);
	}
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		atividadesProdutivasCadastro.excluir(id);
	}
	


}
