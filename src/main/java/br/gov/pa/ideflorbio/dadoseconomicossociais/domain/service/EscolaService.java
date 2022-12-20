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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EscolaReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.LocalidadeDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.EscolaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EscolaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.LocalidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Escola;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EscolasRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.LocalidadesRepository;

@Service
public class EscolaService {
	
	private static final String ENTIDADE_EM_USO 
	= "A Escola de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	EscolasRepository escolas;
	
	@Autowired
	LocalidadesRepository localidades;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public EscolaReciboDTO inserir(EscolaInput escolaInput) {
		
		
		Long idLocalidade = escolaInput.getLocalidade().getId();
		Localidade localidade = localidades.findById(idLocalidade)
		.orElseThrow(()->new LocalidadeNaoEncontradaException(idLocalidade));
		
		
		Escola escola = mapper.map(escolaInput, Escola.class);
		escola.setLocalidade(localidade);
		
		
		return mapper.map(escolas.save(escola), EscolaReciboDTO.class);
	}
	
	
	public EscolaReciboDTO atualizar(Long id, EscolaInput escolaInput) {
		
		
		Escola escolaAtual = escolas.findById(id)
				.orElseThrow(()-> new EscolaNaoEncontradaException(id));
		
		mapper.map(escolaInput, escolaAtual);
		
		EscolaInput novoInput = mapper.map(escolaAtual, EscolaInput.class);
		
		return inserir(novoInput);
	}
	
	
	
	public Page<EscolaReciboDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return escolas.findAll(paginacao).map(p -> mapper.map(p, EscolaReciboDTO.class)); 
		
	}
	
	public EscolaReciboDTO localizarEntidade(Long id) {
		
			Escola escola = escolas.findById(id)
					.orElseThrow(()-> new EscolaNaoEncontradaException(id));
		
		return mapper.map(escola, EscolaReciboDTO.class);
	}
		
	@Transactional
	public void excluir(Long id) {
		try {
			localidades.deleteById(id);
			localidades.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new EscolaNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	
	

}
