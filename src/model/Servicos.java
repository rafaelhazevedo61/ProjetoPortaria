/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rafael
 */
public class Servicos {
    
    int codservico;
    String nome;
    double valor;
    String observacao;

    public Servicos() {
        
    }

    public Servicos(int codservico) {
        this.codservico = codservico;
    }

    public Servicos(String nome) {
        this.nome = nome;
    }
    
    public Servicos(int codservico, String nomeservico, String valor, String observacao) {
        
        this.codservico = codservico;
        this.nome = nomeservico;
        this.valor = Double.parseDouble(valor);
        this.observacao = observacao;
        
    }
    
    public Servicos(String nomeservico, String valor, String observacao) {
        
        this.nome = nomeservico;
        this.valor = Double.parseDouble(valor);
        this.observacao = observacao;
        
    }
    
    public int getCodservico() {
        return codservico;
    }

    public void setCodservico(int codservico) {
        this.codservico = codservico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
        
    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
