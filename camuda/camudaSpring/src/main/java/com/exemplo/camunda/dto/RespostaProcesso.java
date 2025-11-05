package com.exemplo.camunda.dto;

/**
 * DTO para resposta da API em Português
 */
public class RespostaProcesso {
    
    private String idInstanciaProcesso;
    private String idDefinicaoProcesso;
    private String mensagem;
    private String chaveNegocio;
    
    // Getters e Setters
    public String getIdInstanciaProcesso() {
        return idInstanciaProcesso;
    }
    
    public void setIdInstanciaProcesso(String idInstanciaProcesso) {
        this.idInstanciaProcesso = idInstanciaProcesso;
    }
    
    public String getIdDefinicaoProcesso() {
        return idDefinicaoProcesso;
    }
    
    public void setIdDefinicaoProcesso(String idDefinicaoProcesso) {
        this.idDefinicaoProcesso = idDefinicaoProcesso;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getChaveNegocio() {
        return chaveNegocio;
    }
    
    public void setChaveNegocio(String chaveNegocio) {
        this.chaveNegocio = chaveNegocio;
    }
}
