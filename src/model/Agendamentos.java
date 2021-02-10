/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class Agendamentos {
    
    int codagendamento;
    int cliente;
    int barbeiro;
    int servico;
    /*INNER JOIN*/ String clienteString;
    /*INNER JOIN*/ String barbeiroString;
    /*INNER JOIN*/ String servicoString;
    double valor;
    Date data;
    String hora;
    String observacao;

    public Agendamentos() {
        
    }

    public Agendamentos(int codagendamento) {
        
        this.codagendamento = codagendamento;
        
    }
    
    public Agendamentos(int codagendamento, int cliente, int barbeiro, int servico, String valor, String data, String hora, String observacao) {
        
        this.codagendamento = codagendamento;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.valor = Double.parseDouble(valor);
        this.hora = hora;
        this.observacao = observacao;
        
        try{
            
            this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        
        } catch (ParseException ex) {
            
            Logger.getLogger(Agendamentos.class.getName()).log(Level.SEVERE, null, ex);
            
        }    

    }
    
    public Agendamentos(int codcliente, int codbarbeiro, int codservico, String valor, String data, String hora, String observacao) {
        
        this.cliente = codcliente;
        this.barbeiro = codbarbeiro;
        this.servico = codservico;
        this.valor = Double.parseDouble(valor);
        this.hora = hora;
        this.observacao = observacao;
        
        try{
            
            this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        
        } catch (ParseException ex) {
            
            Logger.getLogger(Agendamentos.class.getName()).log(Level.SEVERE, null, ex);
            
        }    

    }

    public int getCodagendamento() {
        return codagendamento;
    }

    public void setCodagendamento(int codagendamento) {
        this.codagendamento = codagendamento;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(int barbeiro) {
        this.barbeiro = barbeiro;
    }

    public int getServico() {
        return servico;
    }

    public void setServico(int servico) {
        this.servico = servico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    //INNER JOIN//

    public String getClienteString() {
        return clienteString;
    }

    public void setClienteString(String clienteString) {
        this.clienteString = clienteString;
    }

    public String getBarbeiroString() {
        return barbeiroString;
    }

    public void setBarbeiroString(String barbeiroString) {
        this.barbeiroString = barbeiroString;
    }

    public String getServicoString() {
        return servicoString;
    }

    public void setServicoString(String servicoString) {
        this.servicoString = servicoString;
    }
    
    
    
    
}
