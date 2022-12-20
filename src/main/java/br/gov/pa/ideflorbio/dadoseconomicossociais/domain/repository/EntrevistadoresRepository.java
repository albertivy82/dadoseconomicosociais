package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Entrevistador;


@Repository
public interface EntrevistadoresRepository extends JpaRepository<Entrevistador, Long>{
	
	@Query(value = "SELECT * FROM entrevistador WHERE entrevistador.nome LIKE:nome", nativeQuery = true)
	Optional<Entrevistador> findByNome(String nome);

}
