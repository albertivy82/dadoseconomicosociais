package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.IndicadoConsultaPublica;


@Repository
public interface IndicadosConsultaPublicaRepository extends JpaRepository<IndicadoConsultaPublica, Long>{
	
	@Query(value = "SELECT * FROM indicado_cosulta_publica WHERE indicado_cosulta_publica.nome LIKE :nome", nativeQuery = true)
	Optional<IndicadoConsultaPublica> findByNome(String nome);
	

}
