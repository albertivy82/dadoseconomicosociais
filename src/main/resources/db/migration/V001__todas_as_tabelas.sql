CREATE TABLE `localidade` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `municipio` varchar(100) NOT NULL,
  `posto_de_saude` varchar(5) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `escola` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `educacao_ambiental` varchar(5) NOT NULL,
  `merenda` varchar(5) NOT NULL,
  `transporte` varchar(5) NOT NULL,
  `localidade` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `entrevistador` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `localidade_entrevistador` (
  `localidade` bigint(30) NOT NULL,
  `entrevistador` bigint(30) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `residencia` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `rua` varchar(255) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `referencial` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `imovel` varchar(100) NOT NULL,
  `data_chegada` date NOT NULL,
  `pretende_mudar` varchar(255) NOT NULL,
  `motivo_vontade_mudanca` varchar(255) DEFAULT NULL,  
  `relacao_area` varchar(255) NOT NULL,
  `relacao_vizinhos` varchar(255) NOT NULL,
  `localidade` bigint(30) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `entrevistado` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `apelido` varchar(100) DEFAULT NULL,
  `naturalidade` varchar(100) DEFAULT NULL,
  `localidade` bigint(30) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;




CREATE TABLE `violencia` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `ocorrencias` int NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;



CREATE TABLE `instituicao_conhecida` (
  `id` bigint(3) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `atividades` varchar(255) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `atividade_produtiva` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `atividade` varchar(255) NOT NULL,
  `pessoas_envolvidas` bigint(30) NOT NULL,
  `faturamento_atividade_mes_total` decimal(19,2) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `renda_outras_fontes` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `fonte` varchar(100) NOT NULL,
  `beneficiarios` bigint(30) NOT NULL,
  `renda_mes_total` decimal(19,2) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `credito` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `residencia` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `morador` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `data_nascimento` date NOT NULL,
  `escolaridade` varchar(255) NOT NULL,
  `estado_civil` varchar(255) NOT NULL,
  `idade` int(3) NOT NULL,
  `onde_estuda` varchar(255) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `religiao` varchar(255) NOT NULL,
  `sexo` varchar(255) NOT NULL,
  `trabalho` varchar(5) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `doenca` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `doenca_nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `morador_doenca` (
  `morador` bigint(30) NOT NULL,
  `doenca` bigint(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `dados_de_consumo` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `alimentacao_principal` varchar(255) NOT NULL,
  `local_de_compras` varchar(255) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `indicado_consulta_publica` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `perfil` varchar(100) NOT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `entrevistado` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `servicos_basicos` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `tipo_atendimento` varchar(100) NOT NULL,
  `servicos_deficitarios` varchar(100) NOT NULL,
  `residencia` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;











