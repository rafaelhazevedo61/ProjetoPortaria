/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaBarbeiro_1_Opcoes;
import view.TelaBarbeiro_2_AlterarExcluir;
import view.TelaBarbeiro_3_Cadastrar;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaBarbeiro_1_OpcoesController {
        
    private TelaBarbeiro_1_Opcoes view;
 
    //CONSTRUTOR
    public TelaBarbeiro_1_OpcoesController(TelaBarbeiro_1_Opcoes view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoAlterarExcluir() {
        
        TelaBarbeiro_2_AlterarExcluir tela = new TelaBarbeiro_2_AlterarExcluir();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    public void botaoCadastrar() {
        
        TelaBarbeiro_3_Cadastrar tela = new TelaBarbeiro_3_Cadastrar();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    
}
