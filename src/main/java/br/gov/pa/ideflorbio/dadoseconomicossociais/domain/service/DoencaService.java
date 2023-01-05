package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.DoencaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DoencaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Doenca;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.DoencasRepository;


@Service
public class DoencaService {
	
	private static final String ENTIDADE_EM_USO = "A doença de id %d nõ pode ser apagada, pois está "
			+ "sendo utilizado em um relacionamento";
	
	@Autowired
	DoencasRepository doencas;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public Doenca inserir(Doenca doenca) {
	    return doencas.save(doenca);	
	}
	
	
	@Transactional
	public Doenca buscarEntidade(Long id) {
		
		Doenca doencaAtual = doencas.findById(id)
				.orElseThrow(()->new DoencaNaoEncontradaException(id));
			
		return doencaAtual;
		
	}
	
	
	
	public List<Doenca> listarTodos( ){
		
		return doencas.findAll();
	}
	
	public DoencaDTO localzarentidade(Long id) {
		
		Doenca doenca = doencas.findById(id)
				.orElseThrow(()->new DoencaNaoEncontradaException(id));
		
		return mapper.map(doenca, DoencaDTO.class);
		
	}
	
	@Transactional
	public void apagar(long id) {
		try {
			doencas.deleteById(id);
			doencas.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new DoencaNaoEncontradaException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
