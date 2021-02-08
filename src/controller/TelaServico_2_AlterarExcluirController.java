/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaServico_1_Opcoes;
import view.TelaServico_2_AlterarExcluir;

/**
 *
 * @author Rafael
 */
public class TelaServico_2_AlterarExcluirController {
            
    private TelaServico_2_AlterarExcluir view;
 
    //CONSTRUTOR
    public TelaServico_2_AlterarExcluirController(TelaServico_2_AlterarExcluir view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaServico_1_Opcoes tela = new TelaServico_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
