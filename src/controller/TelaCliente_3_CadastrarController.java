/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.ClientesDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;
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
        
        /*PREENCHIMENTO OBRIGATORIO*/String nome = view.getjTextFieldNomeCompleto().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String cpf = view.getjFormattedTextFieldCPF().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String email = view.getjTextFieldEmail().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String data_nascimento = view.getjFormattedTextFieldDataNascimento().getText();
        /*PREENCHIMENTO OBRIGATORIO*/int sexo = view.getjComboBoxSexo().getSelectedIndex();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String cep = view.getjFormattedTextFieldCEP().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String rua = view.getjTextFieldRua().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String numero = view.getjTextFieldNumero().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String complemento = view.getjTextFieldComplemento().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String bairro = view.getjTextFieldBairro().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String contato1 = view.getjFormattedTextFieldContatoCelular().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String contato2 = view.getjFormattedTextFieldContatoTelefone().getText();   
        /*PREENCHIMENTO OBRIGATORIO*/
        boolean recebe_email;
        if (view.getjRadioButtonSim().isSelected() == true){
            
            recebe_email = true;
            
        } else {
            
            recebe_email = false;
            
        }
        
        //TRATAMENTO DE CAMPOS NULOS
            if(nome.trim().isEmpty() == false){
                
                if(data_nascimento.trim().isEmpty() == false){
                    
                    if(sexo != 0){
                        
                        if(contato1.trim().isEmpty() == false){
        
                            //ABRINDO CONEXÃO COM O BANCO DE DADOS
                            Connection conexao;
                            conexao = ConnectionFactory.getConnection();

                            //CARREGANDO O CONSTRUTOR DA CLASSE MODELO
                            Clientes novocliente;
                            novocliente = new Clientes(nome, cpf, email, sexo, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email);

                            //PASSANDO O CONSTRUTOR DA CLASSE MODELO COMO PARÂMETRO
                            ClientesDAO dao = new ClientesDAO(conexao);
                            dao.CadastrarCliente(novocliente);
                            
                        } else {
                            //contato1
                            JOptionPane.showMessageDialog(null, "Campo de contato1 não pode estar vazio!");
                            
                        }
                        
                    } else {
                        //sexo
                        JOptionPane.showMessageDialog(null, "Campo de sexo não pode estar vazio!");
                        
                    }
                    
                } else {
                    //data_nascimento
                    JOptionPane.showMessageDialog(null, "Campo de data nascimento não pode estar vazio!");
                    
                }
                
            } else {
                //nome
                JOptionPane.showMessageDialog(null, "Campo de nome não pode estar vazio!");
                
            }
        

        
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
