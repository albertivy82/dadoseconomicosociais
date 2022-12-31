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
	
	
	
	public Page<EscolaReciboDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return escolas.findAll(paginacao).map(p -> mapper.map(p, EscolaReciboDTO.class)); 
		
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
