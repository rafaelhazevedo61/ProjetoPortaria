/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.UsuariosDAO;
import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Usuarios;
import view.TelaUsuario_1_Opcoes;
import view.TelaUsuario_2_AlterarExcluir;

/**
 *
 * @author Rafael
 */
public class TelaUsuario_2_AlterarExcluirController {
            
    private TelaUsuario_2_AlterarExcluir view;
 
    //CONSTRUTOR
    public TelaUsuario_2_AlterarExcluirController(TelaUsuario_2_AlterarExcluir view) {
        this.view = view;
    }
    
    public void mouseClicked() {
        
        JTable tabelaUsuarios = view.getjTableUsuarios();
        
        JTextField usuario = view.getjTextFieldUsuario();
        JPasswordField senha = view.getjPasswordFieldSenha();
        JComboBox permissao = view.getjComboBoxPermissao();
        
        if(tabelaUsuarios.getSelectedRow() != -1){
            
            usuario.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 1).toString());
            senha.setText(tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 2).toString());
            permissao.setSelectedIndex((Integer) tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 3));
   
        }
        
    }
    
    public void atualizarUsuario() {
        
        JTable tabelaUsuarios = view.getjTableUsuarios();
        
        if(tabelaUsuarios.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para alterar um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
            
            int codusuario = ((int)tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(),0));
            String usuario = view.getjTextFieldUsuario().getText();
            String senha = view.getjPasswordFieldSenha().getText();
            Object permissao = view.getjComboBoxPermissao().getSelectedIndex();
            
            Usuarios atualizausuarios;
            atualizausuarios = new Usuarios(codusuario, usuario, senha, permissao);
            
            Connection conexao;
            conexao = ConnectionFactory.getConnection();
            
            UsuariosDAO dao = new UsuariosDAO(conexao);
            dao.AtualizarUsuario(atualizausuarios);
            
        }
        
    }
    
    public void excluirUsuario() {
        
        JTable tabelaUsuarios = view.getjTableUsuarios();
        
        if(tabelaUsuarios.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para excluir um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
       
            int codusuario = ((int)tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(),0));

            Usuarios excluiusuario;
            excluiusuario = new Usuarios(codusuario);

            Connection conexao;
            conexao = ConnectionFactory.getConnection();

            UsuariosDAO dao = new UsuariosDAO(conexao);
            dao.ExcluirUsuario(excluiusuario); 
            
        }
       
    }
    
    public void pesquisaPorUsuario() {
        
        String usuarioPesquisa = view.getjTextFieldPesquisa().getText().toUpperCase();
        tabelaUsuariosPorUsuario(usuarioPesquisa);
        
    }
    
    public void tabelaUsuarios() {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableUsuarios().getModel();
        model.setNumRows(0);
        
        UsuariosDAO dao = new UsuariosDAO(con);

        for (Usuarios c : dao.ListarUsuarios()) {
        
            model.addRow(new Object[]{
                c.getCodusuario(),
                c.getUsuario(),
                c.getSenha(),
                c.getPermissao()
                

            });
        }
    }
    
    public void tabelaUsuariosPorUsuario(String usuariopesquisa) {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableUsuarios().getModel();
        model.setNumRows(0);
        
        UsuariosDAO dao = new UsuariosDAO(con);

        for (Usuarios c : dao.ListarUsuariosPorUsuario(usuariopesquisa)) {
        
            model.addRow(new Object[]{
                c.getCodusuario(),
                c.getUsuario(),
                c.getSenha(),
                c.getPermissao()
                
            });
        }
    }
    
    public void botaoSalvar() {
        
        atualizarUsuario();
        tabelaUsuarios();
        
    }
    
    public void botaoExcluir() {
        
        excluirUsuario();
        tabelaUsuarios();
        
    }
    
    public void botaoPesquisar() {
        
        pesquisaPorUsuario();
        
    }
    
    public void botaoVoltar() {
        
        TelaUsuario_1_Opcoes tela = new TelaUsuario_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
