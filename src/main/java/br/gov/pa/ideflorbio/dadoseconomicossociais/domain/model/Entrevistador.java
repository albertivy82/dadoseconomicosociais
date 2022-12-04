package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Entrevistador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	private Long id;
	
	private String Nome;
	
	@ManyToMany(mappedBy="entrevistador")
	private List<Localidade> localidade;

}
