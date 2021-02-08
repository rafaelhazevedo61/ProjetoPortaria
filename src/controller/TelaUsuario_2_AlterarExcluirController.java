/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    
    public void botaoVoltar() {
        
        TelaUsuario_1_Opcoes tela = new TelaUsuario_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
