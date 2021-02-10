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
import model.Servicos;

/**
 *
 * @author Rafael
 */
public class ServicosDAO {
            
    private final Connection connection;
    
    //CONSTRUTOR
    public ServicosDAO(Connection connection) {
        this.connection = connection;
        
    }
    
    public Servicos CadastrarServico (Servicos servico)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement pst = null;
        
        String sql = "INSERT INTO servicos(\n" +
        "	nome, valor, observacao)\n" +
        "	VALUES (?, ?, ?);";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, servico.getNome().toUpperCase());
            pst.setDouble(2, servico.getValor());
            pst.setString(3, servico.getObservacao());
            
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método CadastrarServicos() na classe ServicosDAO");
            
            
        } 
        
        return servico;
    
    }

    public void AtualizarServico (Servicos atualizaservico) {
               
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        String sql = "UPDATE servicos "
                + "SET nome = ?, valor = ?, observacao = ? "
                + "WHERE codservico = ?";
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, atualizaservico.getNome().toUpperCase());
            stmt.setDouble(2, atualizaservico.getValor());
            stmt.setString(3, atualizaservico.getObservacao());
            stmt.setInt(4, atualizaservico.getCodservico());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro - Método AtualizarServico() em ServicosDAO");
            
        } 
    
    }
    
    public Servicos ExcluirServico(Servicos excluiservico) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        String sql = "delete from servicos where codservico = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, excluiservico.getCodservico());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ExcluirServico() na classe ServicosDAO");
            
        } 
        
        return excluiservico;
    }
    
    public int RetornaCodservicoPorServicos(String servico) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select codservico from servicos where nome = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, servico);
            
            stmt.execute();
            
            rs = stmt.getResultSet();
            
            if(rs.next()){
                
                return rs.getInt("codservico");
                
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método RetornaCodservicoPorServicos() na classe ServicosDAO");
            
        } 
        
        return 0;
        
    }
    
    public double RetornaValorPorServico(String servico) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select valor from servicos where nome = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, servico);
            
            stmt.execute();
            
            rs = stmt.getResultSet();
            
            if(rs.next()){
                
                return rs.getDouble("valor");
                
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método RetornaValorPorServico() na classe ServicosDAO");
            
        } 
        
        return 0;
        
    }
    
    public Iterable<Servicos> ListarServicos() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Servicos> servicos = new ArrayList<>();

        String sql = "SELECT codservico, nome, valor, observacao\n" +
        "	FROM servicos;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                servicos.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarServicos() na classe ServicosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return servicos;
    }
    
    public Iterable<Servicos> ListarServicosJComboBox() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Servicos> servicos = new ArrayList<>();

        String sql = "SELECT nome\n" +
        "	FROM servicos;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                servicos.add(CarregarResultSet2(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarServicosJComboBox() na classe ServicosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return servicos;
    }
    
    public Iterable<Servicos> ListarServicosPorServico(String servico) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Servicos> servicos = new ArrayList<>();

        String sql = "select codservico, nome, valor, observacao "
                + "from servicos "
                + "where nome like ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+servico+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                servicos.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarServicosPorServico() na classe ServicosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return servicos;
    }
    
    public Servicos CarregarResultSet1(ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO TODOS OS CAMPOS
        
        Servicos servico = new Servicos();
        
        servico.setCodservico(rs.getInt("codservico"));
        servico.setNome(rs.getString("nome"));
        servico.setValor(rs.getDouble("valor"));
        servico.setObservacao(rs.getString("observacao"));
        
        return servico;
        
    }
    
    public Servicos CarregarResultSet2 (ResultSet rs) throws SQLException {
        
        //RESULT SET CONTENDO CAMPO NOME
        
        Servicos servico = new Servicos();
        
        servico.setNome(rs.getString("nome"));
        
        return servico;
        
    }
    
}
