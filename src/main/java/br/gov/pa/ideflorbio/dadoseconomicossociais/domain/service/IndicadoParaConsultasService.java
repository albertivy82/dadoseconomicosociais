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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.IndicadoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.IndicadoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.IndicadoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.CreditoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistado;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.IndicadoConsultaPublica;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EntrevistadosRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.IndicadosConsultaPublicaRepository;



@Service
public class IndicadoParaConsultasService {
	
	private static final String ENTIDADE_EM_USO = "O  id %d não pode ser apagado, pois está "
			+ "sendo utilizada em um relacionamento";
	
	@Autowired
	IndicadosConsultaPublicaRepository indicados;
	
	@Autowired
	EntrevistadosRepository entrevistados;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public IndicadoDTO inserir(IndicadoInput indicadoInput) {
		
		Long idEntrevistado = indicadoInput.getEntrevistado().getId();
		Entrevistado entrevistado = entrevistados.findById(idEntrevistado)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idEntrevistado));
		
		IndicadoConsultaPublica indicado = mapper.map(indicadoInput, IndicadoConsultaPublica.class);
		indicado.setEntrevistado(entrevistado);
		
		return mapper.map(indicados.save(indicado), IndicadoDTO.class);	
	}
	
	
	@Transactional
	public IndicadoDTO atualizar(Long id, IndicadoInput indicadoInput) {
		
		IndicadoConsultaPublica indicadoAtual = indicados.findById(id)
				.orElseThrow(()->new IndicadoNaoEncontradoException(id));
		
		mapper.map(indicadoInput, indicadoAtual);
		
		IndicadoInput novoInput = mapper.map(indicadoAtual, IndicadoInput.class);
		
		return inserir(novoInput);
		
	}
	
	
	
	public Page<IndicadoDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
		return indicados.findAll(paginacao).map(p -> mapper.map(p, IndicadoDTO.class));
	}
	
	public IndicadoDTO localzarentidade(Long id) {
		
		IndicadoConsultaPublica indicado = indicados.findById(id)
				.orElseThrow(()->new CreditoNaoEncontradoException(id));
		
		return mapper.map(indicado, IndicadoDTO.class);
		
	}
	
	@Transactional
	public void apagar(long id) {
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
