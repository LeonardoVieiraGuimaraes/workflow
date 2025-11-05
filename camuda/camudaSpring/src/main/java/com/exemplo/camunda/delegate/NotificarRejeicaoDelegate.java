package com.exemplo.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delegate para notificar a rejeição da solicitação
 */
@Component("notificarRejeicaoDelegate")
public class NotificarRejeicaoDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(NotificarRejeicaoDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("=== Notificando rejeição da solicitação ===");
        
        // Obter variáveis do processo
        String solicitante = (String) execution.getVariable("solicitante");
        String descricao = (String) execution.getVariable("descricao");
        Double valor = (Double) execution.getVariable("valor");
        String motivoRejeicao = (String) execution.getVariable("motivoRejeicao");
        String rejeitadoPor = (String) execution.getVariable("rejeitadoPor");
        
        log.info("Solicitação rejeitada!");
        log.info("Solicitante: {}", solicitante);
        log.info("Descrição: {}", descricao);
        log.info("Valor: R$ {}", valor);
        log.info("Rejeitado por: {}", rejeitadoPor);
        log.info("Motivo: {}", motivoRejeicao);
        
        // Aqui você pode adicionar lógica de notificação, como:
        // - Enviar email para o solicitante
        // - Criar notificação no sistema
        // - Registrar log de auditoria
        // - Enviar mensagem para fila
        
        // Simulando envio de notificação
        Thread.sleep(500);
        
        // Definir variável de conclusão
        execution.setVariable("processoStatus", "REJEITADO");
        execution.setVariable("dataRejeicao", new java.util.Date());
        
        log.info("Notificação enviada com sucesso!");
    }
}
