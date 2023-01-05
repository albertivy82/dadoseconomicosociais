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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.DadosDeConsumoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.DadosDeConsumoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.DadosDeConsumo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.DadosDeConsumoService;
import io.swagger.annotations.Api;



@Api(tags = "Dados de Consumo")
@RestController
@RequestMapping("/dados-de-consumo")
public class DadosDeConsumoController {
	
	
	@Autowired
	DadosDeConsumoService dadosDeConsumoCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public DadosDeConsumoDTO adicionar(@RequestBody @Valid DadosDeConsumoInput consumoInput) {
		try{
			DadosDeConsumo consumo = mapper.map(consumoInput, DadosDeConsumo.class);
			return mapper.map(dadosDeConsumoCadastro.inserir(consumo), DadosDeConsumoDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public DadosDeConsumoDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid DadosDeConsumoInput consumoInput) {
		try {
			DadosDeConsumo consumoAtual = dadosDeConsumoCadastro.buscarEntidade(id);
			consumoAtual.setResidencia(new Residencia());
			mapper.map(consumoInput, consumoAtual);
			return mapper.map(dadosDeConsumoCadastro.inserir(consumoAtual), DadosDeConsumoDTO.class); 
			
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		
	}
	
	
	@GetMapping
	public List<DadosDeConsumoDTO> listar(){
		return dadosDeConsumoCadastro
				.listarTodos().stream().map(t->mapper.map(t, DadosDeConsumoDTO.class)).toList();
	}
	
	@GetMapping("/{id}")
	public DadosDeConsumoDTO Buscar(@PathVariable Long id) {
		return dadosDeConsumoCadastro.localizarEntidade(id);
	}
	
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		dadosDeConsumoCadastro.excluir(id);
	}
	


}
