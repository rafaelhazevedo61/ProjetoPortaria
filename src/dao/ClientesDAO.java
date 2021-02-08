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
import model.Clientes;
import util.Data;

/**
 *
 * @author Rafael
 */
public class ClientesDAO {
            
    private final Connection connection;
    
    //CONSTRUTOR
    public ClientesDAO(Connection connection) {
        this.connection = connection;
        
    }
    
    public Clientes CadastrarCliente (Clientes cliente)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement pst = null;
        
        String sql = "INSERT INTO clientes(\n" +
        "	nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email)\n" +
        "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cliente.getNome().toUpperCase());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getEmail());
            pst.setDate(4, Data.ConvertDataFormParaBanco(cliente.getData_nascimento()));
            pst.setInt(5, cliente.getSexo());
            pst.setString(6, cliente.getCep());
            pst.setString(7, cliente.getRua().toUpperCase());
            pst.setString(8, cliente.getNumero().toUpperCase());
            pst.setString(9, cliente.getComplemento().toUpperCase());
            pst.setString(10, cliente.getBairro().toUpperCase());
            pst.setString(11, cliente.getContato1());
            pst.setString(12, cliente.getContato2());
            pst.setBoolean(13, cliente.isRecebe_email());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método CadastrarCliente() na classe ClientesDAO");
            
            
        } 
        
        return cliente;
    
    }
    public void AtualizarCliente(Clientes atualizacliente) {
               
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        String sql = "UPDATE clientes\n" +
        "	SET nome=?, cpf=?, email=?, data_nascimento=?, sexo=?, cep=?, rua=?, numero=?, "
                + "complemento=?, bairro=?, contato1=?, contato2=?, recebe_email=?\n" +
        "	WHERE codcliente=?;";
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, atualizacliente.getNome().toUpperCase());
            stmt.setString(2, atualizacliente.getCpf());
            stmt.setString(3, atualizacliente.getEmail());
            stmt.setDate(4, Data.ConvertDataFormParaBanco(atualizacliente.getData_nascimento()));
            stmt.setInt(5, atualizacliente.getSexo());
            stmt.setString(6, atualizacliente.getCep());
            stmt.setString(7, atualizacliente.getRua().toUpperCase());
            stmt.setString(8, atualizacliente.getNumero().toUpperCase());
            stmt.setString(9, atualizacliente.getComplemento().toUpperCase());
            stmt.setString(10, atualizacliente.getBairro().toUpperCase());
            stmt.setString(11, atualizacliente.getContato1());
            stmt.setString(12, atualizacliente.getContato2());
            stmt.setBoolean(13, atualizacliente.isRecebe_email());
            stmt.setInt(14, atualizacliente.getCodcliente());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro - Método AtualizarCliente() em ClientesDAO");
            
        } 
    
    }
    
    public Clientes ExcluirCliente(Clientes excluicliente) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        String sql = "delete from clientes where codcliente = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, excluicliente.getCodcliente());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ExcluirCliente() na classe ClientesDAO");
            
        } 
        
        return excluicliente;
    }
    
    public Iterable<Clientes> ListarClientes() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Clientes> clientes = new ArrayList<>();

        String sql = "SELECT codcliente, nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email\n" +
        "	FROM clientes;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                clientes.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarClientes() na classe ClientesDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return clientes;
    }
    
    public Iterable<Clientes> ListarClientesPorNome(String nome) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Clientes> clientes = new ArrayList<>();

        String sql = "SELECT codcliente, nome, cpf, email, data_nascimento, sexo, cep, rua, numero, complemento, bairro, contato1, contato2, recebe_email\n" +
        "	FROM clientes "
                + "WHERE nome like ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                clientes.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarClientesPorNome() na classe ClientesDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return clientes;
    }
    
    public Clientes CarregarResultSet1(ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO TODOS OS CAMPOS
        
        Clientes cliente = new Clientes();
        
        cliente.setCodcliente(rs.getInt("codcliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setEmail(rs.getString("email"));
        cliente.setData_nascimento(rs.getDate("data_nascimento"));
        cliente.setSexo(rs.getInt("sexo"));
        cliente.setCep(rs.getString("cep"));
        cliente.setRua(rs.getString("rua"));
        cliente.setNumero(rs.getString("numero"));
        cliente.setComplemento(rs.getString("complemento"));
        cliente.setBairro(rs.getString("bairro"));
        cliente.setContato1(rs.getString("contato1"));
        cliente.setContato2(rs.getString("contato2"));
        cliente.setRecebe_email(rs.getBoolean("recebe_email"));
        
        return cliente;
        
    }
}
