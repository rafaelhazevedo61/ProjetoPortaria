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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Agendamentos;
import util.Data;
import util.Email;
import view.TelaAgendar;
import view.TelaPrincipal;

/**
 *
 * @author Rafael
 */
public class TelaAgendarController {
            
    private TelaAgendar view;
 
    //CONSTRUTOR
    public TelaAgendarController(TelaAgendar view) {
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
    
    public void cadastrarAgendamento(){
        
        /*PREENCHIMENTO OBRIGATORIO*/int clientes = view.getjComboBoxCliente().getSelectedIndex();
        /*PREENCHIMENTO OBRIGATORIO*/int barbeiros = view.getjComboBoxBarbeiro().getSelectedIndex();
        /*PREENCHIMENTO OBRIGATORIO*/int servicos = view.getjComboBoxServico().getSelectedIndex();
        /*PREENCHIMENTO OBRIGATORIO*/String valor = view.getjTextFieldValor().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String data = view.getjFormattedTextFieldData().getText();
        /*PREENCHIMENTO OBRIGATORIO*/String hora = view.getjFormattedTextFieldHora().getText();
        /*PREENCHIMENTO NÃO OBRIGATORIO*/String observacao = view.getjTextAreaObservacao().getText();
        
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

                                //CARREGANDO O CONSTRUTOR DA CLASSE MODELO
                                Agendamentos novoagendamento;
                                novoagendamento = new Agendamentos(codcliente, codbarbeiro, codservico, valor, data, hora, observacao);

                                //PASSANDO O CONSTRUTOR DA CLASSE MODELO COMO PARÂMETRO PARA O MÉTODO NA CLASSE DAO
                                AgendamentosDAO daox = new AgendamentosDAO(conexao);
                                daox.CadastrarAgendamento(novoagendamento);
                                
                                //PASSANDO O CODCLIENTE PARA O MÉTODO EM DAO PARA RETORNAR O EMAIL DO CLIENTE
                                ClientesDAO dao4 = new ClientesDAO(conexao);
                                String emailCliente = dao4.RetornaEmailPorCodcliente(codcliente);
                                System.out.println("Email do cliente é "+emailCliente);
                                
                                //PASSANDO O CODBARBEIRO PARA O MÉTODO EM DAO PARA RETORNAR O EMAIL DO BARBEIRO
                                BarbeirosDAO dao5 = new BarbeirosDAO(conexao);
                                String emailBarbeiro = dao5.RetornaEmailPorCodbarbeiro(codbarbeiro);
                                System.out.println("Email do cliente é "+emailCliente);
                                
                                String emailBarbearia = "rhz.sistemas.projetos@gmail.com";
                                
                                //ENVIO DE EMAIL DO AGENDAMENTO - CLIENTE
                                Email envioEmailCliente = new Email("Agendamento Barbearia xxxyyyzzz",//ASSUNTO
                                "Segue as informações referentes a seu agendamento - cliente: " //CONTEÚDO
                                + "Data: "+ data +" | Hora: "+hora+" | Barbeiro: "+barbeiroString+" | Serviço: "+servicoString+"",  //CONTEÚDO
                                emailCliente); //DESTINATÁRIO
                                
                                //ENVIO DE EMAIL DO AGENDAMENTO - BARBEIRO
                                Email envioEmailBarbeiro = new Email("Agendamento Barbearia xxxyyyzzz",//ASSUNTO
                                "Segue as informações referentes a seu agendamento - barbeiro: " //CONTEÚDO
                                + "Data: "+ data +" | Hora: "+hora+" | Cliente: "+clienteString+" | Serviço: "+servicoString+"",  //CONTEÚDO
                                emailBarbeiro); //DESTINATÁRIO
                                
                                
                                //ENVIO DE EMAIL DO AGENDAMENTO - BARBEARIA
                                Email envioEmailBarbearia = new Email("Agendamento Barbearia xxxyyyzzz",//ASSUNTO
                                "Segue as informações referentes a seu agendamento - barbearia: " //CONTEÚDO
                                + "Data: "+ data +" | Hora: "+hora+" | Barbeiro: "+barbeiroString+" | Cliente: "+clienteString+" | Serviço: "+servicoString+"",  //CONTEÚDO
                                emailBarbearia);//DESTINATÁRIO
                                
        
                                envioEmailCliente.enviar();
                                envioEmailBarbeiro.enviar();
                                envioEmailBarbearia.enviar();


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
    
    public void botaoCadastrar(){
        
        cadastrarAgendamento();
        tabelaAgendamentos();
        
    }
    
    public void botaoVoltar() {
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        view.dispose();
        
    }
    
}
