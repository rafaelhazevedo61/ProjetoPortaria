/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.BarbeirosDAO;
import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Barbeiros;
import util.Data;
import view.TelaBarbeiro_1_Opcoes;
import view.TelaBarbeiro_2_AlterarExcluir;

/**
 *
 * @author Rafael
 */
public class TelaBarbeiro_2_AlterarExcluirController {
            
    private TelaBarbeiro_2_AlterarExcluir view;
 
    //CONSTRUTOR
    public TelaBarbeiro_2_AlterarExcluirController(TelaBarbeiro_2_AlterarExcluir view) {
        this.view = view;
    }
    
    public void mouseClicked(){
        
        JTable tabelaBarbeiros = view.getjTableBarbeiros();
        
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
        
        if(tabelaBarbeiros.getSelectedRow() != -1){
            
            nome.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 1).toString());
            cpf.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 2).toString());
            email.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 3).toString());
            data_nascimento.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 4).toString());
            sexo.setSelectedIndex((Integer) tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 5));
            cep.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 6).toString());
            rua.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 7).toString());
            numero.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 8).toString());
            complemento.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 9).toString());
            bairro.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 10).toString());
            contato1.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 11).toString());
            contato2.setText(tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 12).toString());
            
            if((tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(), 13).toString()).equals("true")){
                
                recebe_email_sim.setSelected(true);
                recebe_email_nao.setSelected(false);
                
            } else {
                                
                recebe_email_sim.setSelected(false);
                recebe_email_nao.setSelected(true);
                
            }
            
        }
        
    }
    
    public void atualizaMorador() {

        JTable tabelaBarbeiros = view.getjTableBarbeiros();

        if(tabelaBarbeiros.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para alterar um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
        
        int codbarbeiro = ((int)tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(),0));
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
        
        Barbeiros atualizabarbeiro;
        atualizabarbeiro = new Barbeiros(codbarbeiro, nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        BarbeirosDAO dao = new BarbeirosDAO(conexao);
        dao.AtualizarBarbeiro(atualizabarbeiro);

        }
        
    }
    
    public void excluiMorador() {

        JTable tabelaBarbeiros = view.getjTableBarbeiros();

        if(tabelaBarbeiros.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para excluir um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
        
        int codbarbeiro = ((int)tabelaBarbeiros.getValueAt(tabelaBarbeiros.getSelectedRow(),0));
        
        Barbeiros excluibarbeiro;
        excluibarbeiro = new Barbeiros(codbarbeiro);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        BarbeirosDAO dao = new BarbeirosDAO(conexao);
        dao.ExcluirBarbeiro(excluibarbeiro);

        }
        
    }
    
    public void pesquisaPorNome() {
        
        String nomePesquisa = view.getjTextFieldPesquisa().getText().toUpperCase();
        tabelaBarbeirosPorNome(nomePesquisa);
        
    }
    
    public void tabelaBarbeiros() {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableBarbeiros().getModel();
        model.setNumRows(0);
        
        BarbeirosDAO dao = new BarbeirosDAO(con);

        for (Barbeiros b : dao.ListarBarbeiros()) {
        
            model.addRow(new Object[]{
                b.getCodbarbeiro(),
                b.getNome(),
                b.getCpf(),
                b.getEmail(),
                Data.Format(b.getData_nascimento()),
                b.getSexo(),
                b.getCep(),
                b.getRua(),
                b.getNumero(),
                b.getComplemento(),
                b.getBairro(),
                b.getContato1(),
                b.getContato2(),
                b.isRecebe_email()
                

            });
        }
    }
    
    public void tabelaBarbeirosPorNome(String nomepesquisa) {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableBarbeiros().getModel();
        model.setNumRows(0);
        
        BarbeirosDAO dao = new BarbeirosDAO(con);

        for (Barbeiros b : dao.ListarBarbeirosPorNome(nomepesquisa)) {
        
            model.addRow(new Object[]{
                b.getCodbarbeiro(),
                b.getNome(),
                b.getCpf(),
                b.getEmail(),
                Data.Format(b.getData_nascimento()),
                b.getSexo(),
                b.getCep(),
                b.getRua(),
                b.getNumero(),
                b.getComplemento(),
                b.getBairro(),
                b.getContato1(),
                b.getContato2(),
                b.isRecebe_email()
                

            });
        }
    }
    
    public void botaoSalvar() {
        
        atualizaMorador();
        tabelaBarbeiros();
        
    }
    
    public void botaoExcluir() {
        
        excluiMorador();
        tabelaBarbeiros();
        
    }
    
    public void botaoPesquisar() {
        
        pesquisaPorNome();
        
    }
    
    public void botaoVoltar() {
        
        TelaBarbeiro_1_Opcoes tela = new TelaBarbeiro_1_Opcoes();
        tela.setVisible(true);
        view.dispose();
        
    }
    
}
