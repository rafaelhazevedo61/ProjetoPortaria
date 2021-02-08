/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaPrincipal;
import view.TelaServico_1_Opcoes;
import view.TelaServico_2_AlterarExcluir;
import view.TelaServico_3_Cadastrar;

/**
 *
 * @author Rafael
 */
public class TelaServico_1_OpcoesController {
        
    private TelaServico_1_Opcoes view;
 
    //CONSTRUTOR
    public TelaServico_1_OpcoesController(TelaServico_1_Opcoes view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoAlterarExcluir() {
        
        TelaServico_2_AlterarExcluir tela = new TelaServico_2_AlterarExcluir();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoCadastrar() {
        
        TelaServico_3_Cadastrar tela = new TelaServico_3_Cadastrar();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
