/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaPrincipal;
import view.TelaUsuario_1_Opcoes;
import view.TelaUsuario_2_AlterarExcluir;
import view.TelaUsuario_3_Cadastrar;

/**
 *
 * @author Rafael
 */
public class TelaUsuario_1_OpcoesController {
        
    private TelaUsuario_1_Opcoes view;
 
    //CONSTRUTOR
    public TelaUsuario_1_OpcoesController(TelaUsuario_1_Opcoes view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoAlterarExcluir() {
        
        TelaUsuario_2_AlterarExcluir tela = new TelaUsuario_2_AlterarExcluir();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoCadastrar() {
        
        TelaUsuario_3_Cadastrar tela = new TelaUsuario_3_Cadastrar();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
