/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaBarbeiro_1_Opcoes;
import view.TelaBarbeiro_3_Cadastrar;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaBarbeiro_3_CadastrarController {
                
    private TelaBarbeiro_3_Cadastrar view;
 
    //CONSTRUTOR
    public TelaBarbeiro_3_CadastrarController(TelaBarbeiro_3_Cadastrar view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaBarbeiro_1_Opcoes tela = new TelaBarbeiro_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
