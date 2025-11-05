package com.exemplo.camunda.config;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.stereotype.Component;

/**
 * Plugin para configurar o Camunda em Português BR
 */
@Component
public class PortuguesLocalePlugin implements ProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        // Configurar locale padrão para Português BR
        java.util.Locale.setDefault(new java.util.Locale("pt", "BR"));
        processEngineConfiguration.setDefaultSerializationFormat("application/json");
        System.out.println("✅ Locale do Camunda configurado para: pt_BR");
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        // Configurações pós-inicialização
    }

    @Override
    public void postProcessEngineBuild(org.camunda.bpm.engine.ProcessEngine processEngine) {
        // Configurações após build do engine
    }
}
