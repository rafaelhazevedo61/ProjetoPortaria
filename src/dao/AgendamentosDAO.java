/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Agendamentos;
import util.Data;

/**
 *
 * @author Rafael
 */
public class AgendamentosDAO {
            
    private final Connection connection;
    
    //CONSTRUTOR
    public AgendamentosDAO(Connection connection) {
        this.connection = connection;
        
    }
    
    public Agendamentos CadastrarAgendamento (Agendamentos agendamento) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement pst = null;
        
        String sql = "insert into agendamentos "
                + "(cliente, barbeiro, servico, valor, data, hora, observacao)"
                + "values(?,?,?,?,?,?,?)";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, agendamento.getCliente());
            pst.setInt(2, agendamento.getBarbeiro());
            pst.setInt(3, agendamento.getServico());
            pst.setDouble(4, agendamento.getValor());
            pst.setDate(5, Data.ConvertDataFormParaBanco(agendamento.getData()));
            pst.setString(6, agendamento.getHora());
            pst.setString(7, agendamento.getObservacao());
            
            pst.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Agendamento cadastrado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método CadastrarAgendamento() na classe AgendamentosDAO");
            
            
        } 
        
        return agendamento;
        
    }

    public void AtualizarAgendamento(Agendamentos atualizaagendamento) {
               
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        String sql = "UPDATE agendamentos\n" +
        "	SET cliente=?, barbeiro=?, servico=?, valor=?, data=?, hora=?, observacao=?\n" +
        "	WHERE codagendamento=?;";
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, atualizaagendamento.getCliente());
            stmt.setInt(2, atualizaagendamento.getBarbeiro());
            stmt.setInt(3, atualizaagendamento.getServico());
            stmt.setDouble(4, atualizaagendamento.getValor());
            stmt.setDate(5, Data.ConvertDataFormParaBanco(atualizaagendamento.getData()));
            stmt.setString(6, atualizaagendamento.getHora());
            stmt.setString(7, atualizaagendamento.getObservacao());
            stmt.setInt(8, atualizaagendamento.getCodagendamento());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agendamento atualizado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AgendamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro - Método AtualizarAgendamento() em AgendamentosDAO");
            
        } 
    
    }
    
    public Agendamentos ExcluirAgendamento(Agendamentos excluiagendamento) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        String sql = "delete from agendamentos where codagendamento = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, excluiagendamento.getCodagendamento());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agendamento excluído com sucesso!");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AgendamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ExcluirAgendamento() na classe AgendamentosDAO");
            
        } 
        
        return excluiagendamento;
    }
    
    public Iterable<Agendamentos> ListarAgendamentos() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Agendamentos> agendamentos = new ArrayList<>();

        String sql = "SELECT codagendamento, cliente, barbeiro, servico, valor, data, hora, observacao\n" +
        "	FROM agendamentos;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                agendamentos.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(AgendamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarAgendamentos() na classe AgendamentosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return agendamentos;
    }
    
    public Iterable<Agendamentos> ListarAgendamentosComInnerJoin() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Agendamentos> agendamentos = new ArrayList<>();

        String sql = "select codagendamento, clientes.nome as nomecliente, barbeiros.nome as nomebarbeiro, servicos.nome as nomeservico, agendamentos.valor, data, hora, agendamentos.observacao\n" +
        "from agendamentos\n" +
        "inner join clientes on clientes.codcliente = agendamentos.cliente\n" +
        "inner join barbeiros on barbeiros.codbarbeiro = agendamentos.barbeiro\n" +
        "inner join servicos on servicos.codservico = agendamentos.servico\n" +
        "order by data desc";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                agendamentos.add(CarregarResultSet2(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(AgendamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarAgendamentos() na classe AgendamentosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return agendamentos;
        
    }
    
    public Iterable<Agendamentos> ListarAgendamentosPorCliente(String cliente) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Agendamentos> agendamentos = new ArrayList<>();

        String sql = "SELECT codagendamento, cliente, barbeiro, servico, valor, data, hora, observacao\n" +
        "	FROM agendamentos "
                + "WHERE cliente like ?;";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+cliente+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                agendamentos.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(AgendamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarAgendamentosPorCliente() na classe AgendamentosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return agendamentos;
    }
    
    public Iterable<Agendamentos> ListarAgendamentosPorClienteComInnerJoin(String cliente) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Agendamentos> agendamentos = new ArrayList<>();

        String sql = "select codagendamento, clientes.nome as nomecliente, barbeiros.nome as nomebarbeiro, servicos.nome as nomeservico, agendamentos.valor, data, hora, agendamentos.observacao\n" +
        "from agendamentos\n" +
        "inner join clientes on clientes.codcliente = agendamentos.cliente\n" +
        "inner join barbeiros on barbeiros.codbarbeiro = agendamentos.barbeiro\n" +
        "inner join servicos on servicos.codservico = agendamentos.servico\n" +
        "where clientes.nome like ?"
                + "order by data desc";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+cliente+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                agendamentos.add(CarregarResultSet2(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(AgendamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarAgendamentosPorCliente() na classe AgendamentosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return agendamentos;
    }
    
    public Agendamentos CarregarResultSet1 (ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO TODOS OS CAMPOS
        
        Agendamentos agendamento = new Agendamentos();
        
        agendamento.setCodagendamento(rs.getInt("codagendamento"));
        agendamento.setCliente(rs.getInt("cliente"));
        agendamento.setBarbeiro(rs.getInt("barbeiro"));
        agendamento.setServico(rs.getInt("servico"));
        agendamento.setValor(rs.getDouble("valor"));
        agendamento.setData(rs.getDate("data"));
        agendamento.setHora(rs.getString("hora"));
        agendamento.setObservacao(rs.getString("observacao"));
        
        return agendamento;
        
    }
    
    public Agendamentos CarregarResultSet2 (ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO TODOS OS CAMPOS - USANDO INNER JOIN
        
        Agendamentos agendamento = new Agendamentos();
        
        agendamento.setCodagendamento(rs.getInt("codagendamento"));
        agendamento.setClienteString(rs.getString("nomecliente"));
        agendamento.setBarbeiroString(rs.getString("nomebarbeiro"));
        agendamento.setServicoString(rs.getString("nomeservico"));
        agendamento.setValor(rs.getDouble("valor"));
        agendamento.setData(rs.getDate("data"));
        agendamento.setHora(rs.getString("hora"));
        agendamento.setObservacao(rs.getString("observacao"));
        
        return agendamento;
        
    }
    
    
}
