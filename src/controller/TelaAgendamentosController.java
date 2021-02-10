/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.ConnectionFactory;
import dao.AgendamentosDAO;
import dao.BarbeirosDAO;
import dao.ClientesDAO;
import dao.ServicosDAO;
import java.sql.Connection;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Agendamentos;
import util.Data;
import view.TelaAgendamentos;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaAgendamentosController {
        
    private TelaAgendamentos view;
 
    //CONSTRUTOR
    public TelaAgendamentosController(TelaAgendamentos view) {
        this.view = view;
    }

    public void retornarValorPorServico(){
        
       String servico = view.getjComboBoxServico().getSelectedItem().toString();

       Connection conexao;
       conexao = ConnectionFactory.getConnection();
       
       ServicosDAO dao = new ServicosDAO(conexao);
       double valor = dao.RetornaValorPorServico(servico);
       String valorString = Double.toString(valor);
       
       view.getjTextFieldValor().setText(valorString);
        
    }
    
    public void mouseClicked(){
        
        JTable tabelaAgendamentos = view.getjTableAgendamentos();
        
//        JComboBox cliente = view.getjComboBoxCliente();
//        JComboBox barbeiro = view.getjComboBoxBarbeiro();
//        JComboBox servico = view.getjComboBoxServico();
        JTextField valor = view.getjTextFieldValor();
        JFormattedTextField data = view.getjFormattedTextFieldData();
        JFormattedTextField hora = view.getjFormattedTextFieldHora();
        JTextArea observacao = view.getjTextAreaObservacao();
        
        if(tabelaAgendamentos.getSelectedRow() != -2){
       
//            cliente.setSelectedIndex((Integer) tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 1));
//            barbeiro.setSelectedIndex((Integer) tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 2));
//            servico.setSelectedIndex((Integer) tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 3));
            valor.setText(tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 4).toString());
            data.setText(tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 5).toString());
            hora.setText(tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 6).toString());
            observacao.setText(tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 7).toString());
            
        }
        
    }
    
    public void atualizarAgendamento() {
        
        JTable tabelaAgendamentos = view.getjTableAgendamentos();

        /*PREENCHIMENTO OBRIGATORIO*/int clientes = view.getjComboBoxCliente().getSelectedIndex();
        /*PREENCHIMENTO OBRIGATORIO*/int barbeiros = view.getjComboBoxBarbeiro().getSelectedIndex();
        /*PREENCHIMENTO OBRIGATORIO*/int servicos = view.getjComboBoxServico().getSelectedIndex();
        /*PREENCHIMENTO OBRIGATORIO*/String valor = view.getjTextFieldValor().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String data = view.getjFormattedTextFieldData().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String hora = view.getjFormattedTextFieldHora().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String observacao = view.getjTextAreaObservacao().getText();

        if(tabelaAgendamentos.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para alterar um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
            
            //TRATAMENTO DE CAMPOS NULOS
            if (clientes != 0){

                if (barbeiros != 0){

                    if (servicos != 0){

                        if(valor.trim().isEmpty() == false){

                            if(data.trim().isEmpty() == false){

                                if(hora.trim().isEmpty() == false){

                                    //ABRINDO CONEXÃO COM O BANCO DE DADOS
                                    Connection conexao;
                                    conexao = ConnectionFactory.getConnection();

                                    //PASSANDO O TEXTO PARA O MÉTODO EM DAO PARA RETORNAR O ID DO CLIENTE
                                    String clienteString = view.getjComboBoxCliente().getSelectedItem().toString();
                                    ClientesDAO dao = new ClientesDAO(conexao);
                                    int codcliente = dao.RetornaCodclientePorClientes(clienteString);
                                    System.out.println("Codcliente é "+codcliente);

                                    //PASSANDO O TEXTO PARA O MÉTODO EM DAO PARA RETORNAR O ID DO BARBEIRO
                                    String barbeiroString = view.getjComboBoxBarbeiro().getSelectedItem().toString();
                                    BarbeirosDAO dao2 = new BarbeirosDAO(conexao);
                                    int codbarbeiro = dao2.RetornaCodbarbeiroPorBarbeiros(barbeiroString);
                                    System.out.println("Codbarbeiro é "+codbarbeiro);

                                    //PASSANDO O TEXTO PARA O MÉTODO EM DAO PARA RETORNAR O ID DO SERVIÇO
                                    String servicoString = view.getjComboBoxServico().getSelectedItem().toString();
                                    ServicosDAO dao3 = new ServicosDAO(conexao);
                                    int codservico = dao3.RetornaCodservicoPorServicos(servicoString);
                                    System.out.println("Codservico é "+codservico);

                                    //RECUPERANDO O ID DO CAMPO SELECIONADO
                                    int codagendamento = ((int)tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(),0));
                                    
                                    //CARREGANDO O CONSTRUTOR DA CLASSE MODELO
                                    Agendamentos atualizaagendamentos;
                                    atualizaagendamentos = new Agendamentos (codagendamento, codcliente, codbarbeiro, codservico, valor, data, hora, observacao);

                                    //PASSANDO O CONSTRUTOR DA CLASSE MODELO COMO PARÂMETRO PARA O MÉTODO NA CLASSE DAO
                                    AgendamentosDAO daox = new AgendamentosDAO(conexao);
                                    daox.AtualizarAgendamento(atualizaagendamentos);


                                } else {
                                    //hora
                                    JOptionPane.showMessageDialog(null, "Campo de hora não pode estar vazio!");

                                }

                            } else {
                                //data
                                JOptionPane.showMessageDialog(null, "Campo de data não pode estar vazio!");

                            }

                        } else {
                            //valor
                            JOptionPane.showMessageDialog(null, "Campo de valor não pode estar vazio!");

                        }

                    } else {
                        //servicos
                        JOptionPane.showMessageDialog(null, "Campo de serviços não pode estar vazio!");

                    }

                } else {
                    //barbeiros
                    JOptionPane.showMessageDialog(null, "Campo de barbeiros não pode estar vazio!");

                }

            } else {
                //clientes
                JOptionPane.showMessageDialog(null, "Campo de clientes não pode estar vazio!");

            }
            
        }
        
    }
    
    public void excluirAgendamento() {

        JTable tabelaAgendametnos = view.getjTableAgendamentos();

        if(tabelaAgendametnos.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null,"Para excluir um cadastro, é preciso selecionar um registro na tabela");
            
        } else {
        
        int codagendamento = ((int)tabelaAgendametnos.getValueAt(tabelaAgendametnos.getSelectedRow(),0));
        
        Agendamentos excluiagendamento;
        excluiagendamento = new Agendamentos(codagendamento);
        
        Connection conexao;
        conexao = ConnectionFactory.getConnection();
        
        AgendamentosDAO dao = new AgendamentosDAO(conexao);
        dao.ExcluirAgendamento(excluiagendamento);

        }
        
    }
    
    public void pesquisaPorCliente() {
        
        String clientePesquisa = view.getjTextFieldPesquisa().getText().toUpperCase();
        tabelaAgendamentosPorCliente(clientePesquisa);
        
    }
    
    public void tabelaAgendamentos() {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableAgendamentos().getModel();
        model.setNumRows(0);
        
        AgendamentosDAO dao = new AgendamentosDAO(con);

        for (Agendamentos c : dao.ListarAgendamentosComInnerJoin()) {
        
            model.addRow(new Object[]{
                c.getCodagendamento(),
                /*INNER JOIN*/c.getClienteString(), 
                /*INNER JOIN*/c.getBarbeiroString(),
                /*INNER JOIN*/c.getServicoString(),
                c.getValor(),
                Data.Format(c.getData()),
                c.getHora(),
                c.getObservacao()
                
            });
        }
        
    }
    
    public void tabelaAgendamentosPorCliente(String clientepesquisa) {
        
        Connection con = ConnectionFactory.getConnection();
        
        DefaultTableModel model = (DefaultTableModel) view.getjTableAgendamentos().getModel();
        model.setNumRows(0);
        
        AgendamentosDAO dao = new AgendamentosDAO(con);

        for (Agendamentos c : dao.ListarAgendamentosPorClienteComInnerJoin(clientepesquisa)) {
        
            model.addRow(new Object[]{
                c.getCodagendamento(),
                /*INNER JOIN*/c.getClienteString(), 
                /*INNER JOIN*/c.getBarbeiroString(),
                /*INNER JOIN*/c.getServicoString(),
                c.getValor(),
                Data.Format(c.getData()),
                c.getHora(),
                c.getObservacao()
                
            });
        }
        
    }
    
    public void botaoSalvar() {
        
        atualizarAgendamento();
        
    }
    
    public void botaoExcluir() {
        
        excluirAgendamento();
        
    }
    
    
    public void botaoPesquisar(){
        
        pesquisaPorCliente();
        
    }
    
    public void limparCampos(){
        
        view.getjComboBoxCliente().setSelectedIndex(0);
        view.getjComboBoxBarbeiro().setSelectedIndex(0);
        view.getjComboBoxServico().setSelectedIndex(0);
        view.getjTextFieldValor().setText("");
        view.getjFormattedTextFieldData().setText("");
        view.getjFormattedTextFieldHora().setText("");
        view.getjTextAreaObservacao().setText("");
        
    }

    public void botaoVoltar() {
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        view.dispose();
        
    }
    
    
    
}
