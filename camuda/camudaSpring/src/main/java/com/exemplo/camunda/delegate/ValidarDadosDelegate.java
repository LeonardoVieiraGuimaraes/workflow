package com.exemplo.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delegate para validar os dados da solicitação
 */
@Component("validarDadosDelegate")
public class ValidarDadosDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(ValidarDadosDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("=== Validando dados da solicitação ===");
        
        // Obter variáveis do processo
        String solicitante = (String) execution.getVariable("solicitante");
        String descricao = (String) execution.getVariable("descricao");
        Double valor = (Double) execution.getVariable("valor");
        
        log.info("Solicitante: {}", solicitante);
        log.info("Descrição: {}", descricao);
        log.info("Valor: R$ {}", valor);
        
        // Lógica de validação simples
        boolean dadosValidos = true;
        
        if (solicitante == null || solicitante.trim().isEmpty()) {
            log.warn("Solicitante não informado");
            dadosValidos = false;
        }
        
        if (descricao == null || descricao.trim().isEmpty()) {
            log.warn("Descrição não informada");
            dadosValidos = false;
        }
        
        if (valor == null || valor <= 0) {
            log.warn("Valor inválido");
            dadosValidos = false;
        }
        
        // Definir variável no processo
        execution.setVariable("dadosValidos", dadosValidos);
        
        if (dadosValidos) {
            log.info("Dados válidos! Prosseguindo para aprovação.");
        } else {
            log.warn("Dados inválidos! Necessário correção.");
        }
    }
}
