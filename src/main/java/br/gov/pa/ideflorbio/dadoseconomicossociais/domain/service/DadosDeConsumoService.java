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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.DadosDeConsumoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.DadosDeConsumoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DadosDeConsumoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.DadosDeConsumo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.DadosDeConsumoRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;

@Service
public class DadosDeConsumoService {
	
	
	private static final String ENTIDADE_EM_USO = "Os dados de Consumo registrados com id %d nõ podem ser apagados, pois estão "
			+ "sendo utilizados em um relacionamento";

	
	@Autowired
	DadosDeConsumoRepository consumo;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public DadosDeConsumoDTO inserir(DadosDeConsumoInput dadosDeConsumoInput) {
		
		Long idResidencia = dadosDeConsumoInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		DadosDeConsumo dadosDeConsumo = mapper.map(dadosDeConsumoInput, DadosDeConsumo.class);
		dadosDeConsumo.setResidencia(residencia);
		
		return mapper.map(consumo.save(dadosDeConsumo), DadosDeConsumoDTO.class);
	}
	
	@Transactional
	public DadosDeConsumoDTO atualizar(Long id, DadosDeConsumoInput dadosDeConsumoInput) {
		
		DadosDeConsumo dadosDeConsumoAtual = consumo.findById(id)
				.orElseThrow(()-> new DadosDeConsumoNaoEncontradoException(id));
		mapper.map(dadosDeConsumoInput, dadosDeConsumoAtual);
		
		DadosDeConsumoInput novoInput = mapper.map(dadosDeConsumoAtual, DadosDeConsumoInput.class);
		
		return inserir(novoInput);
	}
	
	public Page<DadosDeConsumoDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return consumo.findAll(paginacao).map(p -> mapper.map(p, DadosDeConsumoDTO.class)); 
		
	}
	
	public DadosDeConsumoDTO localizarEntidade(Long id) {
		
			DadosDeConsumo dadosDeConsumo = consumo.findById(id)
					.orElseThrow(()-> new DadosDeConsumoNaoEncontradoException(id));
		
		return mapper.map(dadosDeConsumo, DadosDeConsumoDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			consumo.deleteById(id);
			consumo.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new DadosDeConsumoNaoEncontradoException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	


}
