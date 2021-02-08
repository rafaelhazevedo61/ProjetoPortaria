/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaServico_1_Opcoes;
import view.TelaServico_3_Cadastrar;

/**
 *
 * @author Rafael
 */
public class TelaServico_3_CadastrarController {
                
    private TelaServico_3_Cadastrar view;
 
    //CONSTRUTOR
    public TelaServico_3_CadastrarController(TelaServico_3_Cadastrar view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaServico_1_Opcoes tela = new TelaServico_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
