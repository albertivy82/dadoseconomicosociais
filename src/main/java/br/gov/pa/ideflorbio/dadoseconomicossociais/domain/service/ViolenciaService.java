package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ViolenciasSofridasDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ServicoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ViolenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Violencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ViolenciaRepository;


@Service
public class ViolenciaService {
	
	
	private static final String ENTIDADE_EM_USO = "Os dados sobre violência com id %d nõ podem ser apagados, pois estão "
			+ "sendo utilizados em um relacionamento";

	
	@Autowired
	ViolenciaRepository violencias;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public Violencia inserir(Violencia violencia) {
		
		Long idResidencia = violencia.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		violencia.setResidencia(residencia);
		
		return violencias.save(violencia);
	}
	
	
	@Transactional
	public Violencia buscarEntidade(Long id) {
		
		Violencia violenciaAtual = violencias.findById(id)
				.orElseThrow(()->new ViolenciaNaoEncontradaException(id));
				
		return violenciaAtual;
		
	}
	
	public List<Violencia> listarTodos(){
		
	   return violencias.findAll(); 
		
	}
	
	public ViolenciasSofridasDTO localizarEntidade(Long id) {
		
			Violencia violencia = violencias.findById(id)
					.orElseThrow(()-> new ServicoNaoEncontradoException(id));
		
		return mapper.map(violencia, ViolenciasSofridasDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			violencias.deleteById(id);
			violencias.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new ViolenciaNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	


}
