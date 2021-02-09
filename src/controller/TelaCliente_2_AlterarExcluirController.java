/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.ClientesDAO;
import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import util.Data;
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
    
    public void mouseClicked(){
        
        JTable tabelaClientes = view.getjTableClientes();
        
        JTextField nome = view.getjTextFieldNomeCompleto();
        JFormattedTextField cpf = view.getjFormattedTextFieldCPF();
        JTextField email = view.getjTextFieldEmail();
        JFormattedTextField data_nascimento = view.getjFormattedTextFieldDataNascimento();
        JComboBox sexo = view.getjComboBoxSexo();
        JFormattedTextField cep = view.getjFormattedTextFieldCEP();
        JTextField rua = view.getjTextFieldRua();
        JTextField numero = view.getjTextFieldNumero();
        JTextField complemento = view.getjTextFieldComplemento();
        JTextField bairro = view.getjTextFieldBairro();
        JFormattedTextField contato1 = view.getjFormattedTextFieldContatoCelular();
        JFormattedTextField contato2 = view.getjFormattedTextFieldContatoTelefone();
        JRadioButton recebe_email_sim = view.getjRadioButtonSim();
        JRadioButton recebe_email_nao = view.getjRadioButtonNão();
        
        if(tabelaClientes.getSelectedRow() != -1){
            
            nome.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 1).toString());
            cpf.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 2).toString());
            email.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 3).toString());
            data_nascimento.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 4).toString());
            sexo.setSelectedIndex((Integer) tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 5));
            cep.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 6).toString());
            rua.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 7).toString());
            numero.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 8).toString());
            complemento.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 9).toString());
            bairro.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 10).toString());
            contato1.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 11).toString());
            contato2.setText(tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 12).toString());
            
            if((tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(), 13).toString()).equals("true")){
                
                recebe_email_sim.setSelected(true);
                recebe_email_nao.setSelected(false);
                
            } else {
                                
                recebe_email_sim.setSelected(false);
                recebe_email_nao.setSelected(true);
                
            }
            
        }
        
    }
    
    public void atualizarCliente() {

        JTable tabelaClientes = view.getjTableClientes();

        if(tabelaClientes.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para alterar um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
        
        int codcliente = ((int)tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(),0));
        String nome = view.getjTextFieldNomeCompleto().getText();
        String cpf = view.getjFormattedTextFieldCPF().getText();
        String email = view.getjTextFieldEmail().getText();
        String data_nascimento = view.getjFormattedTextFieldDataNascimento().getText();
        Object sexo = view.getjComboBoxSexo().getSelectedIndex();
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
        
        Clientes atualizaclientes;
        atualizaclientes = new Clientes(codcliente, nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        ClientesDAO dao = new ClientesDAO(conexao);
        dao.AtualizarCliente(atualizaclientes);

        }
        
    }
    
    public void excluirCliente() {

        JTable tabelaClientes = view.getjTableClientes();

        if(tabelaClientes.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para excluir um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
        
        int codcliente = ((int)tabelaClientes.getValueAt(tabelaClientes.getSelectedRow(),0));
        
        Clientes excluicliente;
        excluicliente = new Clientes(codcliente);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        ClientesDAO dao = new ClientesDAO(conexao);
        dao.ExcluirCliente(excluicliente);

        }
        
    }
    
    public void pesquisaPorNome() {
        
        String nomePesquisa = view.getjTextFieldPesquisa().getText().toUpperCase();
        tabelaClientesPorNome(nomePesquisa);
        
    }
    
    public void tabelaClientes() {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableClientes().getModel();
        model.setNumRows(0);
        
        ClientesDAO dao = new ClientesDAO(con);

        for (Clientes c : dao.ListarClientes()) {
        
            model.addRow(new Object[]{
                c.getCodcliente(),
                c.getNome(),
                c.getCpf(),
                c.getEmail(),
                Data.Format(c.getData_nascimento()),
                c.getSexo(),
                c.getCep(),
                c.getRua(),
                c.getNumero(),
                c.getComplemento(),
                c.getBairro(),
                c.getContato1(),
                c.getContato2(),
                c.isRecebe_email()
                

            });
        }
    }
    
    public void tabelaClientesPorNome(String nomepesquisa) {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableClientes().getModel();
        model.setNumRows(0);
        
        ClientesDAO dao = new ClientesDAO(con);

        for (Clientes c : dao.ListarClientesPorNome(nomepesquisa)) {
        
            model.addRow(new Object[]{
                c.getCodcliente(),
                c.getNome(),
                c.getCpf(),
                c.getEmail(),
                Data.Format(c.getData_nascimento()),
                c.getSexo(),
                c.getCep(),
                c.getRua(),
                c.getNumero(),
                c.getComplemento(),
                c.getBairro(),
                c.getContato1(),
                c.getContato2(),
                c.isRecebe_email()
                

            });
        }
    }
    
    public void botaoSalvar() {
        
        atualizarCliente();
        tabelaClientes();
        
    }
    
    public void botaoExcluir() {
        
        excluirCliente();
        tabelaClientes();
        
    }
    
    public void botaoPesquisar() {
        
        pesquisaPorNome();
        
    }
    
    public void botaoVoltar() {
        
        TelaCliente_1_Opcoes tela = new TelaCliente_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
