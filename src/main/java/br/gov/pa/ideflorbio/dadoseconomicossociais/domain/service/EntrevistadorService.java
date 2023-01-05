package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EntrevistadorDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntrevistadorNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistador;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EntrevistadoresRepository;

@Service
public class EntrevistadorService {
	
	private static final String ENTIDADE_EM_USO = "O entrevistador de id %d nõ pode ser apagado, pois está "
			+ "sendo utilizado em um relacionamento";
	
	@Autowired
	EntrevistadoresRepository entrevistadores;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public Entrevistador inserir(Entrevistador entrevistador) {
		
		return entrevistadores.save(entrevistador);	
	}
	
	
	@Transactional
	public Entrevistador buscarEntidade(Long id) {
		
		Entrevistador entrevistadorAtual = entrevistadores
				.findById(id).orElseThrow(()->new EntrevistadorNaoEncontradoException(id));
		return entrevistadorAtual;
		
	}
	
	
	
	public List<Entrevistador> listarTodos(){
		
		return entrevistadores.findAll();
	}
	
	
	public EntrevistadorDTO buscaPorId(Long id){
		Entrevistador entrevistador = entrevistadores
				.findById(id).orElseThrow(()->new EntrevistadorNaoEncontradoException(id));
		return mapper.map(entrevistador, EntrevistadorDTO.class);
	}
	
	public EntrevistadorDTO buscarPorNome(String nome) {
		Entrevistador entrevistador = entrevistadores.findByNome(nome)
				.orElseThrow(()->new EntrevistadorNaoEncontradoException(nome));
		
		return mapper.map(entrevistador, EntrevistadorDTO.class);
	}
	
	@Transactional
	public void apagar(long id) {
		try {
			entrevistadores.deleteById(id);
			entrevistadores.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new EntrevistadorNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
