package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.RendaOutrasFontesDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.RendaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.RendaOutrasFontes;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.RendasOutrasFontesRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;


@Service
public class RendasService {
	
	private static final String ENTIDADE_EM_USO = "A fonte de renda de id %d não pode ser apagada, pois está "
			+ "sendo utilizada em um relacionamento";
	
	@Autowired
	RendasOutrasFontesRepository rendas;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public RendaOutrasFontes inserir(RendaOutrasFontes renda) {
		
		Long idResidencia = renda.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		renda.setResidencia(residencia);
		
		return rendas.save(renda);	
	}
	
	
	@Transactional
	public RendaOutrasFontes buscarEntidade(Long id) {
		
		RendaOutrasFontes renda = rendas.findById(id)
				.orElseThrow(()->new RendaNaoEncontradaException(id));
		return renda;
	}
	
	
	
	public List<RendaOutrasFontes> listarTodos(){
		
		return rendas.findAll();
	}
	
	public RendaOutrasFontesDTO localzarentidade(Long id) {
		
		RendaOutrasFontes renda = rendas.findById(id)
				.orElseThrow(()->new RendaNaoEncontradaException(id));
		
		return mapper.map(renda, RendaOutrasFontesDTO.class);
		
	}
	
	@Transactional
	public void excluir(long id) {
		try {
			rendas.deleteById(id);
			rendas.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new RendaNaoEncontradaException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
