package com.exemplo.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delegate para processar a aprovação da solicitação
 */
@Component("processarAprovacaoDelegate")
public class ProcessarAprovacaoDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(ProcessarAprovacaoDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("=== Processando aprovação da solicitação ===");
        
        // Obter variáveis do processo
        String solicitante = (String) execution.getVariable("solicitante");
        String descricao = (String) execution.getVariable("descricao");
        Double valor = (Double) execution.getVariable("valor");
        String aprovador = (String) execution.getVariable("aprovador");
        
        log.info("Solicitação aprovada!");
        log.info("Solicitante: {}", solicitante);
        log.info("Descrição: {}", descricao);
        log.info("Valor: R$ {}", valor);
        log.info("Aprovador: {}", aprovador);
        
        // Aqui você pode adicionar lógica de negócio, como:
        // - Salvar no banco de dados
        // - Enviar email de confirmação
        // - Integrar com outros sistemas
        // - Gerar documentos
        
        // Simulando processamento
        Thread.sleep(1000);
        
        // Definir variável de conclusão
        execution.setVariable("processoStatus", "APROVADO");
        execution.setVariable("dataProcessamento", new java.util.Date());
        
        log.info("Processamento concluído com sucesso!");
    }
}
