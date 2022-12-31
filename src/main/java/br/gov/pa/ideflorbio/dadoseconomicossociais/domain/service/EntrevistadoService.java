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

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EntrevistadoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.AtividadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntrevistadoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistado;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EntrevistadosRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;

@Service
public class EntrevistadoService {
	
	
	private static final String ENTIDADE_EM_USO = "O entrevistado de id %d nõ pode ser apagado, pois está "
			+ "sendo utilizado em um relacionamento";

	
	@Autowired
	EntrevistadosRepository entrevistados;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public Entrevistado inserir(Entrevistado entrevistado) {
		
		Long idResidencia = entrevistado.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		entrevistado.setResidencia(residencia);
		
		return entrevistados.save(entrevistado);
	}
	
	
	@Transactional
	public Entrevistado buscarEntidade(Long id) {
		
		Entrevistado entrevistado = entrevistados.findById(id)
				.orElseThrow(()->new AtividadeNaoEncontradaException(id));	
		
		return entrevistado;
	}
	
	public Page<EntrevistadoDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return entrevistados.findAll(paginacao).map(p -> mapper.map(p, EntrevistadoDTO.class)); 
		
	}
	
	public EntrevistadoDTO localizarEntidade(Long id) {
		
			Entrevistado entrevistado = entrevistados.findById(id)
					.orElseThrow(()-> new EntrevistadoNaoEncontradoException(id));
		
		return mapper.map(entrevistado, EntrevistadoDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			entrevistados.deleteById(id);
			entrevistados.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new EntrevistadoNaoEncontradoException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	


}
