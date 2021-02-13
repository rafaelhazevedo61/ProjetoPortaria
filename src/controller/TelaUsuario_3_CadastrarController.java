/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.UsuariosDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;
import model.Usuarios;
import view.TelaUsuario_1_Opcoes;
import view.TelaUsuario_3_Cadastrar;

/**
 *
 * @author Rafael
 */
public class TelaUsuario_3_CadastrarController {
                
    private TelaUsuario_3_Cadastrar view;
 
    //CONSTRUTOR
    public TelaUsuario_3_CadastrarController(TelaUsuario_3_Cadastrar view) {
        this.view = view;
    }
    
    public void cadastrarNovoServico() {
        
        /*PREENCHIMENTO OBRIGATORIO*/String usuario = view.getjTextFieldUsuario().getText().toUpperCase();
        /*PREENCHIMENTO OBRIGATORIO*/String senha = view.getjPasswordFieldSenha().getText();
        /*PREENCHIMENTO OBRIGATORIO*/int permissao = view.getjComboBoxPermissao().getSelectedIndex();
        
        //TRATAMENTO DE CAMPOS NULOS
        if(usuario.trim().isEmpty() == false){
            
            if(senha.trim().isEmpty() == false){
                
                if(permissao != 0) {
                    
                    //ABRINDO CONEXAO COM O BANCO DE DADOS
                    Connection conexao;
                    conexao = ConnectionFactory.getConnection();
                    
                    //CARREGANDO O CONSTRUTOR DA CLASSE MODELO
                    Usuarios novousuario;
                    novousuario = new Usuarios (usuario, senha, permissao);
                    
                    //PASSANDO O CONSTRUTOR DA CLASSE MODELO COMO PARÂMETRO
                    UsuariosDAO dao = new UsuariosDAO(conexao);
                    dao.CadastrarUsuario(novousuario);
                    
                    
                } else {
                    //permissao
                    JOptionPane.showMessageDialog(null, "Campo de permissao não pode estar vazio!");
                }
                
            } else {
                //senha
                JOptionPane.showMessageDialog(null, "Campo de senha não pode estar vazio!");
            }
            
        } else {
            //usuario
            JOptionPane.showMessageDialog(null, "Campo de usuario não pode estar vazio!");
            
        }
        
    }
    
    public void botaoCadastrar(){
        
        cadastrarNovoServico();
        
    }
    
    public void botaoLimpar(){
        
    }
 
    public void botaoVoltar() {
        
        TelaUsuario_1_Opcoes tela = new TelaUsuario_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
