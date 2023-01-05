package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EntrevistadoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.AtividadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntrevistadoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.NegocioException;
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
		
		if(residencia.getEntrevistado()!=null && entrevistado.getId()==null) {
			throw new NegocioException("Esta residencia já possui um entrevistado cadastrado. Atualize o cadastro ou apague o mesmo"
					+ " para realizar novo cadastro");
		}
		
		entrevistado.setResidencia(residencia);
		
		return entrevistados.save(entrevistado);
	}
	
	
	@Transactional
	public Entrevistado buscarEntidade(Long id) {
		
		Entrevistado entrevistado = entrevistados.findById(id)
				.orElseThrow(()->new AtividadeNaoEncontradaException(id));	
		
		return entrevistado;
	}
	
	public List<Entrevistado> listarTodos(){
		
	   return entrevistados.findAll(); 
		
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
