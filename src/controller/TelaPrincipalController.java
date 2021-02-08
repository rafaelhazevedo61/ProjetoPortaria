/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.TelaAgendamentos;
import view.TelaAgendar;
import view.TelaBarbeiro_1_Opcoes;
import view.TelaCliente_1_Opcoes;
import view.TelaPrincipal;
import view.TelaServico_1_Opcoes;
import view.TelaUsuario_1_Opcoes;

/**
 *
 * @author Rafael
 */
public class TelaPrincipalController {
                
    private TelaPrincipal view;
 
    //CONSTRUTOR
    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;
    }
    
    //BARRA DE MENU - INICIO
    public void menuCliente(){
        
        TelaCliente_1_Opcoes telaCliente = new TelaCliente_1_Opcoes();
        telaCliente.setVisible(true);
        view.dispose();
        
    }
    
    public void menuBarbeiro(){
        
        TelaBarbeiro_1_Opcoes telaBarbeiro = new TelaBarbeiro_1_Opcoes();
        telaBarbeiro.setVisible(true);
        view.dispose();
        
    }
    
    public void menuServico(){
        
        TelaServico_1_Opcoes telaServico = new TelaServico_1_Opcoes();
        telaServico.setVisible(true);
        view.dispose();
        
    }
    
    public void menuUsuario(){
        
        TelaUsuario_1_Opcoes telaUsuario = new TelaUsuario_1_Opcoes();
        telaUsuario.setVisible(true);
        view.dispose();
        
    }
    
    public void menuAgendar(){
        
        TelaAgendar telaAgendar = new TelaAgendar();
        telaAgendar.setVisible(true);
        view.dispose();
        
    }
    
    public void menuAgendamentos(){
        
        TelaAgendamentos telaAgendamentos = new TelaAgendamentos();
        telaAgendamentos.setVisible(true);
        view.dispose();
        
    }
    //BARRA MENU - FIM
    
}
