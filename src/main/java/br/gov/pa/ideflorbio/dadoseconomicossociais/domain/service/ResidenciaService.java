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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaListaDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.ResidenciaReciboDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.ResidenciaInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUso;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.LocalidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.LocalidadesRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;

@Service
public class ResidenciaService {
	
	private static final String ENTIDADE_EM_USO 
	= "A resiência de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	LocalidadesRepository localidades;
	
	@Autowired
	ResidenciasRepository residencias;
	
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public ResidenciaReciboDTO inserir(ResidenciaInput residenciaInput) {
		
		Long idLocalidade = residenciaInput.getLocalidade().getId();
		Localidade localidade = localidades.findById(idLocalidade)
		.orElseThrow(()->new LocalidadeNaoEncontradaException(idLocalidade));
		
		Residencia residencia = mapper.map(residenciaInput, Residencia.class);
		residencia.setLocalidade(localidade);
		
		return mapper.map(residencias.save(residencia), ResidenciaReciboDTO.class);
	}
	
		
	public Page<ResidenciaListaDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
	   return localidades.findAll(paginacao).map(p -> mapper.map(p, ResidenciaListaDTO.class)); 
		
	}
	
	public ResidenciaDTO localizarEntidade(Long id) {
		
			Residencia residencia = residencias.findById(id)
					.orElseThrow(()-> new LocalidadeNaoEncontradaException(id));
		
		return mapper.map(residencia, ResidenciaDTO.class);
	}
	
		
	@Transactional
	public void excluir(Long id) {
		try {
			residencias.deleteById(id);
			residencias.flush();
		}catch(EmptyResultDataAccessException e) {
			
			throw new ResidenciaNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUso(String.format(ENTIDADE_EM_USO, id));
		}
	}	
	
	
	

}
