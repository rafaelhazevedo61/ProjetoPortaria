/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.ServicosDAO;
import java.sql.Connection;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Servicos;
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
    
    public void mouseClicked() {
        
        JTable tabelaServicos = view.getjTableServicos();
        
        JTextField nomeservico = view.getjTextFieldServico();
        JFormattedTextField valor = view.getjFormattedTextFieldValor();
        JTextArea observacao = view.getjTextAreaObservacao();
        
        if(tabelaServicos.getSelectedRow() != -1){
            
            nomeservico.setText(tabelaServicos.getValueAt(tabelaServicos.getSelectedRow(), 1).toString());
            valor.setText(tabelaServicos.getValueAt(tabelaServicos.getSelectedRow(), 2).toString());
            observacao.setText(tabelaServicos.getValueAt(tabelaServicos.getSelectedRow(), 3).toString());
   
        }
        
    }
    
    public void atualizarServico() {
        
        JTable tabelaServicos = view.getjTableServicos();
        
        /*PREENCHIMENTO OBRIGATORIO*/String nomeservico = view.getjTextFieldServico().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String valor = view.getjFormattedTextFieldValor().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String observacao = view.getjTextAreaObservacao().getText();
        
        if(tabelaServicos.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para alterar um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
            
            //TRATAMENTO DE CAMPOS NULOS
            if(nomeservico.trim().isEmpty() == false) {
                
                if(valor.trim().isEmpty() == false) {
            
                    //ABRINDO CONEXAO COM O BANCO DE DADOS
                    Connection conexao;
                    conexao = ConnectionFactory.getConnection();

                    //RECUPERANDO O ID DO CAMPO SELECIONADO
                    int codservico = ((int)tabelaServicos.getValueAt(tabelaServicos.getSelectedRow(),0));

                    //CARREGANDO O CONSTRUTOR DA CLASSE MODELO
                    Servicos atualizaservicos;
                    atualizaservicos = new Servicos(codservico, nomeservico, valor, observacao);

                    //PASSANDO O CONSTRUTOR DA CLASSE MODELO COMO PARÂMETRO PARA O MÉTODO NA CLASSE DAO
                    ServicosDAO dao = new ServicosDAO(conexao);
                    dao.AtualizarServico(atualizaservicos);
                    
                } else {
                    //valor
                    JOptionPane.showMessageDialog(null, "Campo de valor tabelado não pode estar vazio!");
                    
                }
                
            } else {
                //nomeservico
                JOptionPane.showMessageDialog(null, "Campo de nome serviço não pode estar vazio!");
                
            }

        }
        
    }
    
    public void excluirServico() {
        
        JTable tabelaServicos = view.getjTableServicos();
        
        if(tabelaServicos.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para excluir um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
       
            int codservico = ((int)tabelaServicos.getValueAt(tabelaServicos.getSelectedRow(),0));

            Servicos excluiservicos;
            excluiservicos = new Servicos(codservico);

            Connection conexao;
            conexao = ConnectionFactory.getConnection();

            ServicosDAO dao = new ServicosDAO(conexao);
            dao.ExcluirServico(excluiservicos); 
            
        }
       
    }
    
    public void pesquisaPorServico() {
        
        String servicoPesquisa = view.getjTextFieldPesquisa().getText().toUpperCase();
        tabelaServicosPorServico(servicoPesquisa);
        
    }
    
    public void tabelaServicos() {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableServicos().getModel();
        model.setNumRows(0);
        
        ServicosDAO dao = new ServicosDAO(con);

        for (Servicos c : dao.ListarServicos()) {
        
            model.addRow(new Object[]{
                c.getCodservico(),
                c.getNome(),
                c.getValor(),
                c.getObservacao()
                

            });
        }
    }
    
    public void tabelaServicosPorServico(String servicopesquisa) {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableServicos().getModel();
        model.setNumRows(0);
        
        ServicosDAO dao = new ServicosDAO(con);

        for (Servicos c : dao.ListarServicosPorServico(servicopesquisa)) {
        
            model.addRow(new Object[]{
                c.getCodservico(),
                c.getNome(),
                c.getValor(),
                c.getObservacao()
                
            });
        }
    }
    
    public void botaoSalvar() {
        
        atualizarServico();
        tabelaServicos();
        
    }
    
    public void botaoExcluir() {
        
        excluirServico();
        tabelaServicos();
        
    }
    
    public void botaoPesquisar() {
        
        pesquisaPorServico();
        
    }
    
    public void botaoVoltar() {
        
        TelaServico_1_Opcoes tela = new TelaServico_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
