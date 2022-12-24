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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.AtividadeProdutivaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.AtividadeProdutivaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.AtividadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DoencaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.AtividadeProdutiva;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.DadosDeConsumo;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.AtividadesProdutivasRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;


@Service
public class AtividadesProdutivasService {
	
	private static final String ENTIDADE_EM_USO = "A atividade produtiva de id %d nõ pode ser apagada, pois está "
			+ "sendo utilizado em um relacionamento";
	
	@Autowired
	AtividadesProdutivasRepository atividadesProdutivas;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public AtividadeProdutivaDTO inserir(AtividadeProdutivaInput atividadeProdutivaInput) {
		
		Long idResidencia = atividadeProdutivaInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		DadosDeConsumo dadosDeConsumo = mapper.map(atividadeProdutivaInput, DadosDeConsumo.class);
		dadosDeConsumo.setResidencia(residencia);
		
		AtividadeProdutiva atividadeProdutiva = mapper.map(atividadeProdutivaInput, AtividadeProdutiva.class);
		
		return mapper.map(atividadesProdutivas.save(atividadeProdutiva), AtividadeProdutivaDTO.class);	
	}
	
	
	@Transactional
	public AtividadeProdutivaDTO atualizar(Long id, AtividadeProdutivaInput atividadeProdutivaInput) {
		
		AtividadeProdutiva atividadeProdutivaAtual = atividadesProdutivas.findById(id)
				.orElseThrow(()->new DoencaNaoEncontradaException(id));
		
		mapper.map(atividadeProdutivaInput, atividadeProdutivaAtual);
		
		atividadeProdutivaAtual = atividadesProdutivas.save(atividadeProdutivaAtual);
		
		return mapper.map(atividadeProdutivaAtual, AtividadeProdutivaDTO.class);
		
	}
	
	
	
	public Page<AtividadeProdutivaDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
		return atividadesProdutivas.findAll(paginacao).map(p -> mapper.map(p, AtividadeProdutivaDTO.class));
	}
	
	public AtividadeProdutivaDTO localzarentidade(Long id) {
		
		AtividadeProdutiva atividadeProdutiva = atividadesProdutivas.findById(id)
				.orElseThrow(()->new AtividadeNaoEncontradaException(id));
		
		return mapper.map(atividadeProdutiva, AtividadeProdutivaDTO.class);
		
	}
	
	@Transactional
	public void apagar(long id) {
		try {
			atividadesProdutivas.deleteById(id);
			atividadesProdutivas.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new DoencaNaoEncontradaException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
