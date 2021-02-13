/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.ServicosDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;
import model.Servicos;
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
    
    public void cadastrarNovoServico() {
        
        /*PREENCHIMENTO OBRIGATORIO*/String nomeservico = view.getjTextFieldServico().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String valor = view.getjFormattedTextFieldValor().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String observacao = view.getjTextAreaObservacao().getText();
        
            //TRATAMENTO DE CAMPOS NULOS
            if(nomeservico.trim().isEmpty() == false) {
                
                if(valor.trim().isEmpty() == false) {
            
                    //ABRINDO CONEXAO COM O BANCO DE DADOS
                    Connection conexao;
                    conexao = ConnectionFactory.getConnection();

                    //CARREGANDO O CONSTRUTOR DA CLASSE MODELO
                    Servicos novoservico;
                    novoservico = new Servicos (nomeservico, valor, observacao);

                    //PASSANDO O CONSTRUTOR DA CLASSE MODELO COMO PARÂMETRO PARA O MÉTODO NA CLASSE DAO
                    ServicosDAO dao = new ServicosDAO(conexao);
                    dao.CadastrarServico(novoservico);
                    
                } else {
                    //valor
                    JOptionPane.showMessageDialog(null, "Campo de valor tabelado não pode estar vazio!");
                    
                }
                
            } else {
                //nomeservico
                JOptionPane.showMessageDialog(null, "Campo de nome serviço não pode estar vazio!");
                
            }
        
    }
    
    public void botaoCadastrar(){
        
        cadastrarNovoServico();
        
    }
    
    public void botaoLimpar(){
        
    }
    
    public void botaoVoltar() {
        
        TelaServico_1_Opcoes tela = new TelaServico_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
    
}
