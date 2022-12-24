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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.RendaOutrasFontesDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.RendasOutrasFontesInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DoencaNaoEncontradaException;
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
	public RendaOutrasFontesDTO inserir(RendasOutrasFontesInput rendasInput) {
		
		Long idResidencia = rendasInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		RendaOutrasFontes renda = mapper.map(rendasInput, RendaOutrasFontes.class);
		renda.setResidencia(residencia);
		
		return mapper.map(rendas.save(renda), RendaOutrasFontesDTO.class);	
	}
	
	
	@Transactional
	public RendaOutrasFontesDTO atualizar(Long id, RendasOutrasFontesInput rendasInput) {
		
		RendaOutrasFontes rendaAtual = rendas.findById(id)
				.orElseThrow(()->new DoencaNaoEncontradaException(id));
		
		mapper.map(rendasInput, rendaAtual);
		
		rendaAtual = rendas.save(rendaAtual);
		
		return mapper.map(rendaAtual, RendaOutrasFontesDTO.class);
		
	}
	
	
	
	public Page<RendaOutrasFontesDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
		return rendas.findAll(paginacao).map(p -> mapper.map(p, RendaOutrasFontesDTO.class));
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
