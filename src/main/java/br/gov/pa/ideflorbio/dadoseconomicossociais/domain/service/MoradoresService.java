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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.MoradorDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.MoradorInput;
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
	public MoradorDTO inserir(MoradorInput moradorInput) {
		
		Long idResidencia = moradorInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		Morador morador = mapper.map(moradorInput, Morador.class);
		morador.setResidencia(residencia);
		
		return mapper.map(moradores.save(morador), MoradorDTO.class);
	}
	
	@Transactional
	public MoradorDTO atualizar(Long id, MoradorInput moradorInput) {
		
		Morador moradorAtual = moradores.findById(id)
				.orElseThrow(()-> new MoradorNaoEncontradoException(id));
		mapper.map(moradorInput, moradorAtual);
		
		MoradorInput novoInput = mapper.map(moradorAtual, MoradorInput.class);
		
		return inserir(novoInput);
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
				.orElseThrow(()-> new ResidenciaNaoEncontradaException(idMorador));
		
		Doenca doenca = doencas.findById(idDoenca)
				.orElseThrow(()-> new DoencaNaoEncontradaException(idDoenca));
		
		morador.getDoenca().remove(doenca);
		
	}	
	
	
	
	
	
	public Page<MoradorDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return moradores.findAll(paginacao).map(p -> mapper.map(p, MoradorDTO.class)); 
		
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
