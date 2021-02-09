/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaCliente_1_Opcoes;
import view.TelaCliente_2_AlterarExcluir;
import view.TelaCliente_3_Cadastrar;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaCliente_1_OpcoesController {
        
    private TelaCliente_1_Opcoes view;
 
    //CONSTRUTOR
    public TelaCliente_1_OpcoesController(TelaCliente_1_Opcoes view) {
        this.view = view;
    }
    
    public void botaoAlterarExcluir() {
        
        TelaCliente_2_AlterarExcluir tela = new TelaCliente_2_AlterarExcluir();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoCadastrar() {
        
        TelaCliente_3_Cadastrar tela = new TelaCliente_3_Cadastrar();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoVoltar() {
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        view.dispose();
        
    }
    
}
