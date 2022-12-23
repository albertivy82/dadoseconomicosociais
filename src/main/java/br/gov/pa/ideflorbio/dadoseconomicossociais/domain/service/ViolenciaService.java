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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ViolenciasSofridasDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ViolenciaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ServicoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ViolenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Violencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ViolenciaRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;


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
	public ViolenciasSofridasDTO inserir(ViolenciaInput violenciaInput) {
		
		Long idResidencia = violenciaInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		Violencia violencia = mapper.map(violenciaInput, Violencia.class);
		violencia.setResidencia(residencia);
		
		return mapper.map(violencias.save(violencia), ViolenciasSofridasDTO.class);
	}
	
	@Transactional
	public ViolenciasSofridasDTO atualizar(Long id, ViolenciaInput violenciaInput) {
		
		Violencia violenciaAtual = violencias.findById(id)
				.orElseThrow(()-> new ServicoNaoEncontradoException(id));
		mapper.map(violenciaInput, violenciaAtual);
		
		ViolenciaInput novoInput = mapper.map(violenciaAtual, ViolenciaInput.class);
		
		return inserir(novoInput);
	}
	
	public Page<ViolenciasSofridasDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return violencias.findAll(paginacao).map(p -> mapper.map(p, ViolenciasSofridasDTO.class)); 
		
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
