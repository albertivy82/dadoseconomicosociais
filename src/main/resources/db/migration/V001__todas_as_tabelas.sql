CREATE TABLE `localidade` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  `posto_de_saude` varchar(5) NOT NULL,
  `referencial` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `escola` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `educacao_ambiental` varchar(5) DEFAULT NULL,
  `merenda` varchar(5) NOT NULL,
  `transporte` varchar(5) NOT NULL,
  `localidade` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `entrevistador` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `localidade_entrevistador` (
  `localidade` bigint(30) NOT NULL,
  `entrevistador` bigint(30) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `residencia` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `localidade` varchar(255) DEFAULT NULL,
  `referencial` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `moradia_conexao` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `data_chegada` date NOT NULL,
  `pretende_mudar` varchar(255) DEFAULT NULL,
  `relacao_area` varchar(255) DEFAULT NULL,
  `relacao_vizinhos` varchar(255) DEFAULT NULL,
  `entrevistado` bigint(30) DEFAULT NULL,
  `residencia` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `entrevistado` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `alimentacao` varchar(255) DEFAULT NULL,
  `compras` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `serv_publico` varchar(255) DEFAULT NULL,
  `tipo_atendimento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `violencia` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `ocorrencias` int NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `entrevistado` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;



CREATE TABLE `instituicao_conhecida` (
  `id` bigint(3) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `atividades` varchar(255) DEFAULT NULL,
  `entrevistado` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `atividade_economica` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `atividade` varchar(255) DEFAULT NULL,
  `faturamento_mes` decimal(19,2) NOT NULL,
  `entrevistado` bigint(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `credito` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `entrevistado` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `morador` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `data_nascimento` date NOT NULL,
  `escolaridade` varchar(255) NOT NULL,
  `estado_civil` varchar(255) NOT NULL,
  `idade` int(3) NOT NULL,
  `onde_estuda` varchar(255) NOT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  `religiao` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) NOT NULL,
  `trabalho` varchar(5) NOT NULL,
  `entrevistado` bigint(30) DEFAULT NULL,
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