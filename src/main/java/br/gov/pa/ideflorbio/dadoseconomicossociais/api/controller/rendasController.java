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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.RendaOutrasFontesDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.RendasOutrasFontesInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.RendaOutrasFontes;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.RendasService;
import io.swagger.annotations.Api;



@Api(tags = "Fontes de Renda")
@RestController
@RequestMapping("/outras-forntes-de-renda")
public class rendasController {
	
	
	@Autowired
	RendasService rendasCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public RendaOutrasFontesDTO adicionar(@RequestBody @Valid RendasOutrasFontesInput rendasInput) {
		try {
			RendaOutrasFontes renda = mapper.map(rendasInput, RendaOutrasFontes.class);
			return mapper.map(rendasCadastro.inserir(renda), RendaOutrasFontesDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	@GetMapping
	public List<RendaOutrasFontesDTO> listar(){
		return rendasCadastro
				.listarTodos().stream().map(t->mapper.map(t, RendaOutrasFontesDTO.class)).toList();
	}
	
	@GetMapping("/{id}")
	public RendaOutrasFontesDTO Buscar(@PathVariable Long id) {
		return rendasCadastro.localzarentidade(id);
	}
	
	@PutMapping("/{id}")
	public RendaOutrasFontesDTO atualizar(@PathVariable Long id, 
			@RequestBody @Valid RendasOutrasFontesInput rendasInput) {
		try {
			RendaOutrasFontes renda = rendasCadastro.buscarEntidade(id);
			renda.setResidencia(new Residencia());
			mapper.map(rendasInput, renda);
			return mapper.map(rendasCadastro.inserir(renda), RendaOutrasFontesDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		rendasCadastro.excluir(id);
	}
	


}
