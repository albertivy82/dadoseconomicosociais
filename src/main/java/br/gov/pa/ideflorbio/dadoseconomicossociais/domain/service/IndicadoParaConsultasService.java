package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.IndicadoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.CreditoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.IndicadoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.NegocioException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistado;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.IndicadoConsultaPublica;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EntrevistadosRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.IndicadosConsultaPublicaRepository;



@Service
public class IndicadoParaConsultasService {
	
	private static final String ENTIDADE_EM_USO = "O indicado para participar"
			+ "das consultas de id %d não pode ser apagado, pois está "
			+ "sendo utilizada em um relacionamento";
	
	@Autowired
	IndicadosConsultaPublicaRepository indicados;
	
	@Autowired
	EntrevistadosRepository entrevistados;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public IndicadoConsultaPublica inserir(IndicadoConsultaPublica indicado) {
		
		Long idEntrevistado = indicado.getEntrevistado().getId();
		Entrevistado entrevistado = entrevistados.findById(idEntrevistado)
				.orElseThrow(()->new ResidenciaNaoEncontradaException(idEntrevistado));
		
		if(entrevistado.getIndicado()!=null && indicado.getId()==null) {
			throw new NegocioException("O Entrevistado informado já indicou alguém. Atualize o cadastro ou apague o mesmo"
					+ " para realizar novo cadastro");
		}
		
		indicado.setEntrevistado(entrevistado);
		
		return indicados.save(indicado);	
	}
	
	
	@Transactional
	public IndicadoConsultaPublica buscarEntidade(Long id) {
		
		IndicadoConsultaPublica indicado = indicados.findById(id)
				.orElseThrow(()->new IndicadoNaoEncontradoException(id));
		
		return indicado;
	}
	
	
	
	public List<IndicadoConsultaPublica> listarTodos(){
		
		return indicados.findAll();
	}
	
	public IndicadoDTO localzarEntidade(Long id) {
		
		IndicadoConsultaPublica indicado = indicados.findById(id)
				.orElseThrow(()->new CreditoNaoEncontradoException(id));
		
		return mapper.map(indicado, IndicadoDTO.class);
		
	}
	
	@Transactional
	public void excluir(long id) {
		try {
			indicados.deleteById(id);
			indicados.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new IndicadoNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
