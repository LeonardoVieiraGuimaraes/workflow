package com.exemplo.camunda.controller;

import com.exemplo.camunda.dto.RespostaProcesso;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para gerenciar processos Camunda
 */
@RestController
@RequestMapping("/api/processo")
public class ProcessoController {

    private static final Logger log = LoggerFactory.getLogger(ProcessoController.class);

    @Autowired
    private RuntimeService runtimeService;

    /**
     * Inicia um novo processo de aprovação
     * 
     * @param request dados da solicitação
     * @return informações da instância do processo criada
     */
    @PostMapping("/iniciar")
    public ResponseEntity<RespostaProcesso> iniciarProcesso(@RequestBody SolicitacaoRequest request) {
        log.info("🚀 Iniciando processo de aprovação para: {}", request.getSolicitante());
        
        // Preparar variáveis do processo
        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("solicitante", request.getSolicitante());
        variaveis.put("descricao", request.getDescricao());
        variaveis.put("valor", request.getValor());
        
        // Iniciar processo
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
            "processo-aprovacao", 
            variaveis
        );
        
        // Preparar resposta em português
        RespostaProcesso resposta = new RespostaProcesso();
        resposta.setIdInstanciaProcesso(processInstance.getProcessInstanceId());
        resposta.setIdDefinicaoProcesso(processInstance.getProcessDefinitionId());
        resposta.setChaveNegocio(processInstance.getBusinessKey());
        resposta.setMensagem("✅ Processo iniciado com sucesso!");
        
        log.info("✅ Processo criado com ID: {}", processInstance.getProcessInstanceId());
        
        return ResponseEntity.ok(resposta);
    }
    
    /**
     * Classe interna para representar a requisição de solicitação
     */
    public static class SolicitacaoRequest {
        private String solicitante;
        private String descricao;
        private Double valor;
        
        // Getters e Setters
        public String getSolicitante() {
            return solicitante;
        }
        
        public void setSolicitante(String solicitante) {
            this.solicitante = solicitante;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
        
        public Double getValor() {
            return valor;
        }
        
        public void setValor(Double valor) {
            this.valor = valor;
        }
    }
}
