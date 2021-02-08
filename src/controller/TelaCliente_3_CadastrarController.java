/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaCliente_1_Opcoes;
import view.TelaCliente_3_Cadastrar;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaCliente_3_CadastrarController {
                
    private TelaCliente_3_Cadastrar view;
 
    //CONSTRUTOR
    public TelaCliente_3_CadastrarController(TelaCliente_3_Cadastrar view) {
        this.view = view;
    }
    
    public void botaoVoltar() {
        
        TelaCliente_1_Opcoes tela = new TelaCliente_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
