ALTER TABLE `escola` ADD CONSTRAINT `fk_localidade_escola` FOREIGN KEY ( `localidade` ) REFERENCES `localidade` ( `id` );
ALTER TABLE `localidade_entrevistador` ADD CONSTRAINT `fk_entrevistador_localidade_entrevistador` FOREIGN KEY ( `entrevistador` ) REFERENCES `entrevistador` ( `id` );
ALTER TABLE `localidade_entrevistador` ADD CONSTRAINT `fk_localidade_localidade_entrevistador` FOREIGN KEY ( `localidade` ) REFERENCES `localidade` ( `id` );
ALTER TABLE `moradia_conexao` ADD CONSTRAINT `fk_residencia_moradia_conexao` FOREIGN KEY ( `residencia` ) REFERENCES `residencia` ( `id` );
ALTER TABLE `moradia_conexao` ADD CONSTRAINT `fk_entrevistado_moradia_conexao` FOREIGN KEY ( `entrevistado` ) REFERENCES `entrevistado` ( `id` );
ALTER TABLE `violencia` ADD CONSTRAINT `fk_entrevistado_violencia` FOREIGN KEY ( `entrevistado` ) REFERENCES `entrevistado` ( `id` );

ALTER TABLE `instituicao_conhecida` ADD CONSTRAINT `fk_entrevistado_instituicao_conhecida` FOREIGN KEY ( `entrevistado` ) REFERENCES `entrevistado` ( `id` );
ALTER TABLE `atividade_economica` ADD CONSTRAINT `fk_entrevistado_atividade_economica` FOREIGN KEY ( `entrevistado` ) REFERENCES `entrevistado` ( `id` );

ALTER TABLE `credito` ADD CONSTRAINT `fk_entrevistado_credito` FOREIGN KEY ( `entrevistado` ) REFERENCES `entrevistado` ( `id` );

ALTER TABLE `morador` ADD CONSTRAINT `fk_entrevistado_morador` FOREIGN KEY ( `entrevistado` ) REFERENCES `entrevistado` ( `id` );

ALTER TABLE `morador_doenca` ADD CONSTRAINT `fk_morador_morador_doenca` FOREIGN KEY ( `morador` ) REFERENCES `morador` ( `id` );
ALTER TABLE `morador_doenca` ADD CONSTRAINT `fk_doenca_morador_doenca` FOREIGN KEY ( `doenca` ) REFERENCES `doenca` ( `id` );