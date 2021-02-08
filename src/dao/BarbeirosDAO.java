/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Barbeiros;
import util.Data;

/**
 *
 * @author Rafael
 */
public class BarbeirosDAO {
            
    private final Connection connection;
    
    //CONSTRUTOR
    public BarbeirosDAO(Connection connection) {
        this.connection = connection;
        
    }
    
    public Barbeiros CadastrarBarbeiro (Barbeiros barbeiro)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement pst = null;
        
        String sql = "INSERT INTO barbeiros(\n" +
        "	nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email)\n" +
        "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, barbeiro.getNome().toUpperCase());
            pst.setString(2, barbeiro.getCpf());
            pst.setString(3, barbeiro.getEmail());
            pst.setDate(4, Data.ConvertDataFormParaBanco(barbeiro.getData_nascimento()));
            pst.setInt(5, barbeiro.getSexo());
            pst.setString(6, barbeiro.getCep());
            pst.setString(7, barbeiro.getRua().toUpperCase());
            pst.setString(8, barbeiro.getNumero().toUpperCase());
            pst.setString(9, barbeiro.getComplemento().toUpperCase());
            pst.setString(10, barbeiro.getBairro().toUpperCase());
            pst.setString(11, barbeiro.getContato1());
            pst.setString(12, barbeiro.getContato2());
            pst.setBoolean(13, barbeiro.isRecebe_email());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Barbeiro cadastrado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(BarbeirosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método CadastrarBarbeiro() na classe BarbeirosDAO");
            
            
        } 
        
        return barbeiro;
    
    }
    
    public void AtualizarBarbeiro(Barbeiros atualizabarbeiro) {
               
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        String sql = "UPDATE barbeiros\n" +
        "	SET nome=?, cpf=?, email=?, data_nascimento=?, sexo=?, cep=?, rua=?, numero=?, "
                + "complemento=?, bairro=?, contato1=?, contato2=?, recebe_email=?\n" +
        "	WHERE codbarbeiro=?;";
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, atualizabarbeiro.getNome().toUpperCase());
            stmt.setString(2, atualizabarbeiro.getCpf());
            stmt.setString(3, atualizabarbeiro.getEmail().toUpperCase());
            stmt.setDate(4, Data.ConvertDataFormParaBanco(atualizabarbeiro.getData_nascimento()));
            stmt.setInt(5, atualizabarbeiro.getSexo());
            stmt.setString(6, atualizabarbeiro.getCep());
            stmt.setString(7, atualizabarbeiro.getRua().toUpperCase());
            stmt.setString(8, atualizabarbeiro.getNumero().toUpperCase());
            stmt.setString(9, atualizabarbeiro.getComplemento().toUpperCase());
            stmt.setString(10, atualizabarbeiro.getBairro().toUpperCase());
            stmt.setString(11, atualizabarbeiro.getContato1());
            stmt.setString(12, atualizabarbeiro.getContato2());
            stmt.setBoolean(13, atualizabarbeiro.isRecebe_email());
            stmt.setInt(14, atualizabarbeiro.getCodbarbeiro());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Barbeiro atualizado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(BarbeirosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro - Método AtualizarBarbeiro() em BarbeirosDAO");
            
        } 
    
    }
    
    public Barbeiros ExcluirBarbeiro(Barbeiros excluibarbeiro) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        String sql = "delete from barbeiros where codbarbeiro = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, excluibarbeiro.getCodbarbeiro());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Barbeiro xcluído com sucesso!");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(BarbeirosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método Excluir() na classe MoradoresDAO");
            
        } 
        
        return excluibarbeiro;
    }
    
    public Iterable<Barbeiros> ListarBarbeiros() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Barbeiros> barbeiros = new ArrayList<>();

        String sql = "SELECT codbarbeiro, nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email\n" +
        "	FROM barbeiros;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                barbeiros.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(BarbeirosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarBarbeiros() na classe BarbeirosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return barbeiros;
    }
    
    public Iterable<Barbeiros> ListarBarbeirosPorNome(String nome) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Barbeiros> barbeiros = new ArrayList<>();

        String sql = "SELECT codbarbeiro, nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email\n" +
        "	FROM barbeiros "
                + "WHERE nome like ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                barbeiros.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(BarbeirosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarBarbeiros() na classe BarbeirosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return barbeiros;
    }
    
    public Barbeiros CarregarResultSet1(ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO TODOS OS CAMPOS
        
        Barbeiros barbeiro = new Barbeiros();
        
        barbeiro.setCodbarbeiro(rs.getInt("codbarbeiro"));
        barbeiro.setNome(rs.getString("nome"));
        barbeiro.setCpf(rs.getString("cpf"));
        barbeiro.setEmail(rs.getString("email"));
        barbeiro.setData_nascimento(rs.getDate("data_nascimento"));
        barbeiro.setSexo(rs.getInt("sexo"));
        barbeiro.setCep(rs.getString("cep"));
        barbeiro.setRua(rs.getString("rua"));
        barbeiro.setNumero(rs.getString("numero"));
        barbeiro.setComplemento(rs.getString("complemento"));
        barbeiro.setBairro(rs.getString("bairro"));
        barbeiro.setContato1(rs.getString("contato1"));
        barbeiro.setContato2(rs.getString("contato2"));
        barbeiro.setRecebe_email(rs.getBoolean("recebe_email"));
        
        return barbeiro;
        
    }
    
}
