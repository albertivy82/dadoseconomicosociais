package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUso;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntrevistadorNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistador;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EntrevistadoresRepository;

@Service
public class EntrevistadorService {
	
	private static final String ENTIDADE_EM_USO = "O entrevistador de id %d nõ pode ser apagado, pois está "
			+ "sendo utilizado em um relacionamento";
	
	@Autowired
	EntrevistadoresRepository entrevistadores;
	
	@Transactional
	public Entrevistador inserir(Entrevistador entrevistador) {
		return entrevistadores.save(entrevistador);
	}
	
	@Transactional
	public List<Entrevistador> listarTodos(){
		return entrevistadores.findAll();
	}
	
	@Transactional
	public Entrevistador buscaPorId(Long id){
		Entrevistador entrevistador = entrevistadores
				.findById(id).orElseThrow(()->new EntrevistadorNaoEncontradoException(id));
		return entrevistador;
	}
	
	public Entrevistador buscarPorNome(String nome) {
		Entrevistador entrevistador = entrevistadores.findByNome(nome)
				.orElseThrow(()->new EntrevistadorNaoEncontradoException(nome));
		
		return entrevistador;
	}
	
	@Transactional
	public void apagar(long id) {
		try {
			entrevistadores.deleteById(id);
			entrevistadores.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new EntrevistadorNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUso(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
