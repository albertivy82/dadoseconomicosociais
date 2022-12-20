package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Localidade;

@Repository
public interface LocalidadesRepository extends JpaRepository<Localidade, Long>{

	@Query(value = "SELECT * FROM localidade WHERE localidade.nome LIKE :nome", nativeQuery = true)
	Optional<Localidade> findByNome(String nome);
	
}
