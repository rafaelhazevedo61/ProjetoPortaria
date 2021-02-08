/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.UsuariosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuarios;
import view.TelaLogin;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaLoginController {
    
    private TelaLogin view;
 
    //CONSTRUTOR
    public TelaLoginController(TelaLogin view) {
        this.view = view;
    }
    
    public void autenticar() {

        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        Usuarios novousuario;
        novousuario = new Usuarios (usuario,senha);
        
        try {
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        UsuariosDAO usuarioDAO = new UsuariosDAO(conexao);
        
        boolean existe;
        
        existe = usuarioDAO.existeUsuario(novousuario);

        
        if(existe == true) {
            
            JOptionPane.showMessageDialog(null,"Usuario autenticado com sucesso");
            System.setProperty("usuarioLogado", usuario);
            
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            view.dispose();
            
        } else {
            
            JOptionPane.showMessageDialog(null,"Usuário ou senha inválidos");

        }
          
        } catch (SQLException ex) {
            
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public void botaoEntrar(){
        
        autenticar();
        
    }
}
