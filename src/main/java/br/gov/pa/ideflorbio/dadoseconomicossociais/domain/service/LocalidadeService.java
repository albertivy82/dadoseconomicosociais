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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.LocalidadeInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.LocalidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.LocalidadesRepository;

@Service
public class LocalidadeService {
	
	private static final String ENTIDADE_EM_USO 
	= "A localidade de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	LocalidadesRepository localidades;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public LocalidadeReciboDTO inserir(LocalidadeInput localidadeInput) {
		
		Localidade localidade = mapper.map(localidadeInput, Localidade.class);
		
		return mapper.map(localidades.save(localidade), LocalidadeReciboDTO.class);
	}
	
	@Transactional
	public LocalidadeReciboDTO atualizar(Long id, LocalidadeInput localidadeInput) {
		
		Localidade localidadeAtual = localidades.findById(id)
				.orElseThrow(()-> new LocalidadeNaoEncontradaException(id));
		
		mapper.map(localidadeInput, localidadeAtual);
		
		localidadeAtual = localidades.save(localidadeAtual);
		
		return mapper.map(localidadeAtual, LocalidadeReciboDTO.class);
		
	}
	
	
	public Page<LocalidadeDTO> listarTodos(@PageableDefault (size = 10) Pageable paginacao){
		
	   return localidades.findAll(paginacao).map(p -> mapper.map(p, LocalidadeDTO.class)); 
		
	}
	
	public LocalidadeDTO localizarEntidade(Long id) {
		
			Localidade localidade = localidades.findById(id)
					.orElseThrow(()-> new LocalidadeNaoEncontradaException(id));
		
		return mapper.map(localidade, LocalidadeDTO.class);
	}
		
	@Transactional
	public void excluir(Long id) {
		try {
			localidades.deleteById(id);
			localidades.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new LocalidadeNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	
	

}
