/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaUsuario_1_Opcoes;
import view.TelaUsuario_3_Cadastrar;

/**
 *
 * @author Rafael
 */
public class TelaUsuario_3_CadastrarController {
                
    private TelaUsuario_3_Cadastrar view;
 
    //CONSTRUTOR
    public TelaUsuario_3_CadastrarController(TelaUsuario_3_Cadastrar view) {
        this.view = view;
    }
 
    public void botaoVoltar() {
        
        TelaUsuario_1_Opcoes tela = new TelaUsuario_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
