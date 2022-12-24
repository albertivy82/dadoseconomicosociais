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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.InstituicoesConhecidasDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.InstituicaoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.InstituicaoNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.InstituicaoConhecida;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.InstituicoesConhecidasRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;

@Service
public class InstituicoesService {
	
	private static final String ENTIDADE_EM_USO 
	= "A insituição de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	InstituicoesConhecidasRepository instituicoes;
	
	@Autowired
	ResidenciasRepository residencias;
	
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public InstituicoesConhecidasDTO inserir(InstituicaoInput instituicaoInput) {
		
		Long idResidencia = instituicaoInput.getResidencia().getId();
		
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		InstituicaoConhecida instituicaoConhecida  = mapper.map(instituicaoInput, InstituicaoConhecida.class);
		instituicaoConhecida.setResidencia(residencia);
		
		return mapper.map(instituicoes.save(instituicaoConhecida), InstituicoesConhecidasDTO.class);
	}
	
	@Transactional
	public InstituicoesConhecidasDTO atualizar(Long id, InstituicaoInput instituicoesInput) {
		
		InstituicaoConhecida instituicaoAtual = instituicoes.findById(id)
				.orElseThrow(()-> new InstituicaoNaoEncontradaException(id));
		mapper.map(instituicoesInput, instituicaoAtual);
		
		InstituicaoInput novoInput = mapper.map(instituicaoAtual, InstituicaoInput.class);
		
		return inserir(novoInput);
	}
	
	public Page<InstituicoesConhecidasDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return instituicoes.findAll(paginacao).map(p -> mapper.map(p, InstituicoesConhecidasDTO.class)); 
		
	}
	
	public InstituicoesConhecidasDTO localizarEntidade(Long id) {
		
		InstituicaoConhecida instituicaoAtual = instituicoes.findById(id)
				.orElseThrow(()-> new InstituicaoNaoEncontradaException(id));
		
		return mapper.map(instituicaoAtual, InstituicoesConhecidasDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			instituicoes.deleteById(id);
			instituicoes.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new InstituicaoNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	
	

}
