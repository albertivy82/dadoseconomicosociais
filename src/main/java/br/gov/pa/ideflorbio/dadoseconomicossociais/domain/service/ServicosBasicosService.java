package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ServicosBasicosDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ServicosBasicosInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ServicoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.ServicosBasicos;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ServicosBasicosRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;
@Service
public class ServicosBasicosService {
	
	
	private static final String ENTIDADE_EM_USO = "Os dados sobre serviços públis básicos com id %d nõ podem ser apagados, pois estão "
			+ "sendo utilizados em um relacionamento";

	
	@Autowired
	ServicosBasicosRepository servicos;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public ServicosBasicosDTO inserir(ServicosBasicosInput servicosBasicosInput) {
		
		Long idResidencia = servicosBasicosInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		ServicosBasicos servicosBasicos = mapper.map(servicosBasicosInput, ServicosBasicos.class);
		servicosBasicos.setResidencia(residencia);
		
		return mapper.map(servicos.save(servicosBasicos), ServicosBasicosDTO.class);
	}
	
	@Transactional
	public ServicosBasicosDTO atualizar(Long id, ServicosBasicosInput servicosBasicosInput) {
		
		ServicosBasicos servicosBasicosAtual = servicos.findById(id)
				.orElseThrow(()-> new ServicoNaoEncontradoException(id));
		
			
		mapper.map(servicosBasicosInput, servicosBasicosAtual);
		
		
		ServicosBasicosInput novoInput = mapper.map(servicosBasicosAtual, ServicosBasicosInput.class);
		
		//Aqui ele está deixando de ser o objeto aqui em cima
		
		return inserir(novoInput);
	}
	
	public Page<ServicosBasicosDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return servicos.findAll(paginacao).map(p -> mapper.map(p, ServicosBasicosDTO.class)); 
		
	}
	
	public ServicosBasicosDTO localizarEntidade(Long id) {
		
			ServicosBasicos ServicosBasicos = servicos.findById(id)
					.orElseThrow(()-> new ServicoNaoEncontradoException(id));
		
		return mapper.map(ServicosBasicos, ServicosBasicosDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			servicos.deleteById(id);
			servicos.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new ServicoNaoEncontradoException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	


}
