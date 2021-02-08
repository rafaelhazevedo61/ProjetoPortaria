/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.ClientesDAO;
import java.sql.Connection;
import model.Clientes;
import view.TelaCliente_1_Opcoes;
import view.TelaCliente_3_Cadastrar;

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
    
    public void cadastrarNovoCliente(){
        
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
        
        Clientes novocliente;
        novocliente = new Clientes(nome, cpf, email, sexo, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        ClientesDAO dao = new ClientesDAO(conexao);
        dao.CadastrarCliente(novocliente);
        
    }
    public void botaoCadastrar(){
        
        cadastrarNovoCliente();
        
    }
    
    public void botaoVoltar() {
        
        TelaCliente_1_Opcoes tela = new TelaCliente_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
