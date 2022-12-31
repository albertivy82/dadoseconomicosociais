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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.MoradorDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.MoradorInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Morador;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.MoradoresService;



@RestController
@RequestMapping("/moradores")
public class MoradorController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	MoradoresService moradoresCadastro;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public MoradorDTO adicionar(@RequestBody @Valid MoradorInput moradorInput) {
		
		try {
			Morador morador = mapper.map(moradorInput, Morador.class);
			return mapper.map(moradoresCadastro.inserir(morador), MoradorDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public MoradorDTO atualizar(@PathVariable Long id,
			@RequestBody @Valid MoradorInput moradorInput) {
		
		try {
			 Morador morador =  moradoresCadastro.buscarEntidade(id);
			 morador.setResidencia(new Residencia());
			 mapper.map(moradorInput, morador);
			return mapper.map(moradoresCadastro.inserir(morador), MoradorDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	@GetMapping
	public Page<MoradorDTO> listar(Pageable paginacao){
		return moradoresCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public MoradorDTO Buscar(@PathVariable Long id) {
		return moradoresCadastro.localizarEntidade(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		moradoresCadastro.excluir(id);
	}
	
	@PutMapping("/{id}/doencas/{idDoencas}")
	public void vincularDoenca(@PathVariable Long id, @PathVariable Long idDoencas) {
		moradoresCadastro.vincularDoenca(id, idDoencas);
	}
	
	@DeleteMapping("/{id}/doencas/{idDoencas}")
	public void desvincularDoenca(@PathVariable Long id, @PathVariable Long idDoencas) {
		moradoresCadastro.desvicunlarDoenca(id, idDoencas);
	}

}
