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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.IndicadoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.IndicadoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntrevistadoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistado;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.IndicadoConsultaPublica;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.IndicadoParaConsultasService;



@RestController
@RequestMapping("/indicado-cosultas")
public class IndicadoConsultaController {
	
	
	@Autowired
	IndicadoParaConsultasService indicadoCadastro;
	
	@Autowired
	ModelMapper mapper;
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public IndicadoDTO adicionar(@RequestBody @Valid IndicadoInput indicadoInput) {
		
		try {
			IndicadoConsultaPublica indicado = mapper.map(indicadoInput, IndicadoConsultaPublica.class);
			return mapper.map(indicadoCadastro.inserir(indicado), IndicadoDTO.class);
		}catch(EntrevistadoNaoEncontradoException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public IndicadoDTO atualizar(@PathVariable Long id,
			@RequestBody @Valid IndicadoInput indicadoInput) {
		
		try {
			 IndicadoConsultaPublica indicado =  indicadoCadastro.buscarEntidade(id);
			 indicado.setEntrevistado(new Entrevistado());
			 mapper.map(indicadoInput, indicado);
			return mapper.map(indicadoCadastro.inserir(indicado), IndicadoDTO.class);
		}catch(EntrevistadoNaoEncontradoException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}

	@GetMapping
	public Page<IndicadoDTO> listar(Pageable paginacao){
		return indicadoCadastro.listarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public IndicadoDTO Buscar(@PathVariable Long id) {
		return indicadoCadastro.localzarEntidade(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarRegistro (@PathVariable Long id) {
		indicadoCadastro.excluir(id);
	}
	


}
