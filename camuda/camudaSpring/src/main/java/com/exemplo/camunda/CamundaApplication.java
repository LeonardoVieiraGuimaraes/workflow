package com.exemplo.camunda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Camunda BPMN
 * 
 * Esta aplicação usa o Camunda Platform 7 com Spring Boot
 * para executar processos de negócio definidos em BPMN.
 */
@SpringBootApplication
public class CamundaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

}
