package br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.Imovel;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.model.enums.SimNao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Residencia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@EqualsAndHashCode.Include
	private Long id;
	
	private String rua;
	
	
	private String numero;
	
	
	private String bairro;
	
	@NotBlank
	private String referencial;
	
	@NotBlank
	private String latitude;
	
	@NotBlank
	private String longitude;
	
	
	@Enumerated(EnumType.STRING)
	private Imovel imovel;
	
	
	@NotNull
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dataChegada;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private SimNao pretendeMudar;

	private String MotivoVontadeMudança;
	
	@NotBlank
	private String relacaoArea;
	
	@NotBlank
	private String relacaoVizinhos;
	
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name="localidade")
	private Localidade localidade;
	
	@JsonIgnore
	@OneToOne(mappedBy="residencia")
	private Entrevistado entrevistado;
	
	@JsonIgnore
	@OneToOne(mappedBy = "residencia")
	private DadosDeConsumo consumo;
	
	@JsonIgnore
	@OneToOne(mappedBy = "residencia")
	private ServicosBasicos servicosBasicos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residencia")
	private List<Violencia> violenciaSofrida;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residencia")
	private List<InstituicaoConhecida> instituicaoConhecida;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residencia")
	private List<AtividadeProdutiva> atividadeProdutiva;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residencia")
	private List<RendaOutrasFontes> rendaOutrasFontes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residencia")
	private List<Credito> credito;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residencia")
	private  List<Morador> morador;
	
}
