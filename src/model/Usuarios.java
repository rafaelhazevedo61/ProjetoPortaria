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
public class Usuarios {
    
    int codusuario;
    String usuario;
    String senha;
    int permissao;

    public Usuarios() {
        
    }

    public Usuarios(int codusuario) {
        
        this.codusuario = codusuario;
        
    }
    
    public Usuarios(String usuario, String senha) {
        
        this.usuario = usuario;
        this.senha = senha;
        
    }

    public Usuarios(int codusuario, String usuario, String senha, Object permissao) {
        
        this.codusuario = codusuario;
        this.usuario = usuario;
        this.senha = senha;
        this.permissao = (Integer) permissao;
        
    }

    public Usuarios(String usuario, String senha, int permissao) {
        
        this.usuario = usuario;
        this.senha = senha;
        this.permissao = (Integer) permissao;
        
    }

    public int getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(int codusuario) {
        this.codusuario = codusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }
    
    
   
    
}
