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
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.CreditoDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.input.CreditoInput;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.DoencaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.CreditoNaoEncontradoException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.exceptions.ResidenciaNaoEncontradaException;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Credito;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Residencia;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.CreditosRepository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.ResidenciasRepository;


@Service
public class CreditoService {
	
	private static final String ENTIDADE_EM_USO = "O crédito de id %d não pode ser apagado, pois está "
			+ "sendo utilizada em um relacionamento";
	
	@Autowired
	CreditosRepository creditos;
	
	@Autowired
	ResidenciasRepository residencias;
	
	@Autowired
	ModelMapper mapper;
	
	@Transactional
	public CreditoDTO inserir(CreditoInput creditoInput) {
		
		Long idResidencia = creditoInput.getResidencia().getId();
		Residencia residencia = residencias.findById(idResidencia)
		.orElseThrow(()->new ResidenciaNaoEncontradaException(idResidencia));
		
		Credito Credito = mapper.map(creditoInput, Credito.class);
		Credito.setResidencia(residencia);
		
		return mapper.map(creditos.save(Credito), CreditoDTO.class);	
	}
	
	
	@Transactional
	public CreditoDTO atualizar(Long id, CreditoInput creditoInput) {
		
		Credito creditoAtual = creditos.findById(id)
				.orElseThrow(()->new DoencaNaoEncontradaException(id));
		
		mapper.map(creditoInput, creditoAtual);
		
		creditoAtual = creditos.save(creditoAtual);
		
		return mapper.map(creditoAtual, CreditoDTO.class);
		
	}
	
	
	
	public Page<CreditoDTO> listarTodos(@PageableDefault (page = 10) Pageable paginacao){
		
		return creditos.findAll(paginacao).map(p -> mapper.map(p, CreditoDTO.class));
	}
	
	public CreditoDTO localzarentidade(Long id) {
		
		Credito Credito = creditos.findById(id)
				.orElseThrow(()->new CreditoNaoEncontradoException(id));
		
		return mapper.map(Credito, CreditoDTO.class);
		
	}
	
	@Transactional
	public void apagar(long id) {
		try {
			creditos.deleteById(id);
			creditos.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new CreditoNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
		
		
	}

}
