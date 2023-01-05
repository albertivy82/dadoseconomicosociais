package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.MoradorDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DoencaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.MoradorNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Doenca;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Morador;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.DoencasRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.MoradoresRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;

@Service
public class MoradoresService {
	
	private static final String ENTIDADE_EM_USO 
	= "O morador de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	MoradoresRepository moradores;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	DoencasRepository doencas;
	
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public Morador inserir(Morador morador) {
		
		Long idResidencia = morador.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		morador.setResidencia(residencia);
		
		return moradores.save(morador);
	}
	
	
	@Transactional
	public Morador buscarEntidade(Long id) {
		
		Morador moradorAtual = moradores.findById(id)
				.orElseThrow(()->new MoradorNaoEncontradoException(id));
				
		return moradorAtual;
		
	}
	@Transactional
	public void vincularDoenca(Long idMorador, Long idDoenca) {
		Morador morador = moradores.findById(idMorador)
				.orElseThrow(()-> new ResidenciaNaoEncontradaException(idMorador));
		
		Doenca doenca = doencas.findById(idDoenca)
				.orElseThrow(()-> new DoencaNaoEncontradaException(idDoenca));
		
		morador.getDoenca().add(doenca);
		
	}	
	
	@Transactional
	public void desvicunlarDoenca(Long idMorador, Long idDoenca) {
		Morador morador = moradores.findById(idMorador)
				.orElseThrow(()-> new MoradorNaoEncontradoException(idMorador));
		
		Doenca doenca = doencas.findById(idDoenca)
				.orElseThrow(()-> new DoencaNaoEncontradaException(idDoenca));
		
		morador.getDoenca().remove(doenca);
		
	}	
	
	public List<Morador> listarTodos(){
		
	   return moradores.findAll(); 
		
	}
	
	public MoradorDTO localizarEntidade(Long id) {
		
			Morador morador = moradores.findById(id)
					.orElseThrow(()-> new ResidenciaNaoEncontradaException(id));
		
		return mapper.map(morador, MoradorDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			moradores.deleteById(id);
			moradores.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new MoradorNaoEncontradoException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	
	

}
