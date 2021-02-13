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
        
        if(usuario.trim().isEmpty() == false){
            
            if(senha.trim().isEmpty() == false){
                
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
                
            } else {
                //senha
                JOptionPane.showMessageDialog(null, "Campo de senha não pode estar vazio!");
                
            }
            
        } else {
            //usuario
            JOptionPane.showMessageDialog(null, "Campo de usuario não pode estar vazio!");
            
        }
        
    }
    
    public void verificaPermissao() {
        
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        try{
            
            Connection conexao;
            conexao = ConnectionFactory.getConnection();
            
            Usuarios permissaoUsuario;
            permissaoUsuario = new Usuarios (usuario, senha);
            
            UsuariosDAO dao = new UsuariosDAO(conexao);
            UsuariosDAO dao2 = new UsuariosDAO(conexao);
            UsuariosDAO dao3 = new UsuariosDAO(conexao);
            
            boolean permissaoRecepcionista;
            permissaoRecepcionista = dao.VerificarPermissaoRecepcionista(permissaoUsuario);
            
            boolean permissaoBarbeiro;
            permissaoBarbeiro = dao2.VerificarPermissaoBarbeiro(permissaoUsuario);
            
            boolean permissaoAdministrador;
            permissaoAdministrador = dao3.VerificarPermissaoAdministrador(permissaoUsuario);
            
            if (permissaoRecepcionista == true){
            
            System.out.println("Permissao de Recepcionista!");
            System.setProperty("permissaoUsuario", "Recepcionista");
            
            } else {

                System.out.println("Sem permissao de recepcionista");

            }

            if (permissaoBarbeiro == true) {

                System.out.println("Permissao de Barbeiro");
                System.setProperty("permissaoUsuario", "Barbeiro");

            } else {

                System.out.println("Sem permissao de barbeiro");

            }

            if (permissaoAdministrador == true) {

                System.out.println("Permissao de Administrador");
                System.setProperty("permissaoUsuario", "Administrador");

            } else {

                System.out.println("Sem permissao de administrador");

            }
            
        } catch (SQLException ex) {

                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);

                }
        
    }
    
    public void botaoEntrar(){
        
        autenticar();
        
    }
}
