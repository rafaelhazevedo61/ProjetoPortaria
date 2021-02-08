/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.BarbeirosDAO;
import java.sql.Connection;
import model.Barbeiros;
import view.TelaBarbeiro_1_Opcoes;
import view.TelaBarbeiro_3_Cadastrar;

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
    
    public void cadastrarNovoBarbeiro(){
        
        String nome = view.getjTextFieldNomeCompleto().getText();
        String cpf = view.getjFormattedTextFieldCPF().getText();
        String email = view.getjTextFieldEmail().getText();
        String data_nascimento = view.getjFormattedTextFieldDataNascimento().getText();
        int sexo = view.getjComboBoxSexo().getSelectedIndex();
        String cep = view.getjFormattedTextFieldCEP().getText();
        String rua = view.getjTextFieldRua().getText();
        String numero = view.getjTextFieldNumero().getText();
        String complemento = view.getjTextFieldComplemento().getText();
        String bairro = view.getjTextFieldBairro().getText();
        String contato1 = view.getjFormattedTextFieldContatoCelular().getText();
        String contato2 = view.getjFormattedTextFieldContatoTelefone().getText();
        
        boolean recebe_email;
        if (view.getjRadioButtonSim().isSelected() == true){
            
            recebe_email = true;
            
        } else {
            
            recebe_email = false;
            
        }
        
        Barbeiros novobarbeiro;
        novobarbeiro = new Barbeiros(nome, cpf, email, sexo, data_nascimento, 0, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        BarbeirosDAO dao = new BarbeirosDAO(conexao);
        dao.CadastrarBarbeiro(novobarbeiro);
        
    }
    
    public void botaoCadastrar(){
        
        cadastrarNovoBarbeiro();
        
    }
    
    public void botaoLimpar() {
        
    }
    
    public void botaoVoltar() {
        
        TelaBarbeiro_1_Opcoes tela = new TelaBarbeiro_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
