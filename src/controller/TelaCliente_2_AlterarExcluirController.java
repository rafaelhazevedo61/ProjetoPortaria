/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaCliente_1_Opcoes;
import view.TelaCliente_2_AlterarExcluir;

/**
 *
 * @author Rafael
 */
public class TelaCliente_2_AlterarExcluirController {
            
    private TelaCliente_2_AlterarExcluir view;
 
    //CONSTRUTOR
    public TelaCliente_2_AlterarExcluirController(TelaCliente_2_AlterarExcluir view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaCliente_1_Opcoes tela = new TelaCliente_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
