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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.DoencaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.doencaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DoencaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntrevistadorNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Doenca;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.DoencasRepository;


@Service
public class DoencaService {
	
	private static final String ENTIDADE_EM_USO = "A doença de id %d nõ pode ser apagada, pois está "
			+ "sendo utilizado em um relacionamento";
	
	@Autowired
	DoencasRepository doencas;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public DoencaDTO inserir(doencaInput doencaInput) {
		
		Doenca doenca = mapper.map(doencaInput, Doenca.class);
		
		return mapper.map(doencas.save(doenca), DoencaDTO.class);	
	}
	
	
	@Transactional
	public DoencaDTO atualizar(Long id, doencaInput doencaInput) {
		
		Doenca doencaAtual = doencas.findById(id)
				.orElseThrow(()->new DoencaNaoEncontradaException(id));
		
		mapper.map(doencaInput, doencaAtual);
		
		doencaAtual = doencas.save(doencaAtual);
		
		return mapper.map(doencaAtual, DoencaDTO.class);
		
	}
	
	
	
	public Page<DoencaDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
		return doencas.findAll(paginacao).map(p -> mapper.map(p, DoencaDTO.class));
	}
	
	
	public DoencaDTO buscarPorNome(String nome) {
		Doenca doenca = doencas.findByName(nome)
				.orElseThrow(()->new EntrevistadorNaoEncontradoException(nome));
		
		return mapper.map(doenca, DoencaDTO.class);
	}
	
	@Transactional
	public void apagar(long id) {
		try {
			doencas.deleteById(id);
			doencas.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new EntrevistadorNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}