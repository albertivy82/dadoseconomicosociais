package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.Doenca;

@Repository
public interface DoencasRepository extends JpaRepository<Doenca, Long>{
	
	Optional<Doenca>findByName(String nome);

}
