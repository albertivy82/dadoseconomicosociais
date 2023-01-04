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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.CreditoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.CreditoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Credito;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.CreditoService;




@RestController
@RequestMapping("/credito")
public class CreditoController {
	
	
	@Autowired
	CreditoService creditoCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public CreditoDTO adicionar(@RequestBody @Valid CreditoInput creditoInput) {
		
		try {
			Credito credito = mapper.map(creditoInput, Credito.class);
			return mapper.map(creditoCadastro.inserir(credito), CreditoDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public CreditoDTO atualizar(@PathVariable Long id,
			@RequestBody @Valid CreditoInput creditoInput) {
		
		try {
			 Credito credito =  creditoCadastro.buscarEntidade(id);
			 credito.setResidencia(new Residencia());
			 mapper.map(creditoInput, credito);
			return mapper.map(creditoCadastro.inserir(credito), CreditoDTO.class);
		}catch(ResidenciaNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
	
	@GetMapping
	public Page<CreditoDTO> listar(Pageable paginacao){
		return creditoCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public CreditoDTO Buscar(@PathVariable Long id) {
		return creditoCadastro.localzarentidade(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		creditoCadastro.excluir(id);
	}
	


}
