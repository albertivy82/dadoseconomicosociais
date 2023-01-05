package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EscolaReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EscolaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.LocalidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Escola;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EscolasRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.LocalidadesRepository;

@Service
public class EscolaService {
	
	private static final String ENTIDADE_EM_USO 
	= "A Escola de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	EscolasRepository escolas;
	
	@Autowired
	LocalidadesRepository localidades;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public Escola inserir(Escola escola) {
		
		
		Long idLocalidade = escola.getLocalidade().getId();
		Localidade localidade = localidades.findById(idLocalidade)
		.orElseThrow(()->new LocalidadeNaoEncontradaException(idLocalidade));
		escola.setLocalidade(localidade);
		
		return escolas.save(escola);
	}
	
	
	public Escola buscarEntidade(Long id) {
		
		Escola escolaAtual = escolas.findById(id)
				.orElseThrow(()-> new EscolaNaoEncontradaException(id));
		return inserir(escolaAtual);
	}
	
	
	
	public List<Escola> listarTodos(){
		
	   return escolas.findAll(); 
		
	}
	
	public EscolaReciboDTO localizarEntidade(Long id) {
		
			Escola escola = escolas.findById(id)
					.orElseThrow(()-> new EscolaNaoEncontradaException(id));
		
		return mapper.map(escola, EscolaReciboDTO.class);
	}
		
	@Transactional
	public void excluir(Long id) {
		try {
			localidades.deleteById(id);
			localidades.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new EscolaNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	
	

}
