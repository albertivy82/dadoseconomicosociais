package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository.EntrevistadosRepository;

@Service
public class EntrevistadoService {
	
	@Autowired
	EntrevistadosRepository entrevistado;
	
	

}
