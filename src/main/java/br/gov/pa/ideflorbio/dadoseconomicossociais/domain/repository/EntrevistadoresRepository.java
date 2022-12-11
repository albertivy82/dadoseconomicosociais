package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistador;

@Repository
public interface EntrevistadoresRepository extends JpaRepository<Entrevistador, Long>{
	
	Optional<Entrevistador> findByNome(String nome);

}
