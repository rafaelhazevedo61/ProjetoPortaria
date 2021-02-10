/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class Barbeiros {
    
    int codbarbeiro;
    String nome;
    String cpf;
    String email;
    Date data_nascimento;
    Integer sexo;
    String cep;
    String rua;
    String numero;
    String complemento;
    String bairro;
    String contato1;
    String contato2;
    boolean recebe_email;

    public Barbeiros() {
       
    }
    
    
    public Barbeiros(int codbarbeiro) {
        
        this.codbarbeiro = codbarbeiro;
        
    }
    
    public Barbeiros(int codbarbeiro, String nome, String cpf, String email, String data_nascimento, Object sexo, String cep, String rua, String numero, String complemento, String bairro, String contato1, String contato2, boolean recebe_email) {
                
        this.codbarbeiro = codbarbeiro;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.sexo = (Integer) sexo;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.recebe_email = recebe_email;
        
        try {
            
            this.data_nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(data_nascimento);
            
        } catch (ParseException ex) {
            
            Logger.getLogger(Barbeiros.class.getName()).log(Level.SEVERE, null, ex);
            
        }    
        
    }
    
    public Barbeiros(String nome, String cpf, String email, int sexo, String data_nascimento, int i, String cep, String rua, String numero, String complemento, String bairro, String contato1, String contato2, boolean recebe_email) {
        
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.sexo = sexo;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.recebe_email = recebe_email;
        
        try {
            
            this.data_nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(data_nascimento);
            
        } catch (ParseException ex) {
            
            Logger.getLogger(Barbeiros.class.getName()).log(Level.SEVERE, null, ex);
            
        }    
        
    }

    public int getCodbarbeiro() {
        return codbarbeiro;
    }

    public void setCodbarbeiro(int codbarbeiro) {
        this.codbarbeiro = codbarbeiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getContato1() {
        return contato1;
    }

    public void setContato1(String contato1) {
        this.contato1 = contato1;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
    }

    public boolean isRecebe_email() {
        return recebe_email;
    }

    public void setRecebe_email(boolean recebe_email) {
        this.recebe_email = recebe_email;
    }
        
    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
