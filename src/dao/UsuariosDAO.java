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
import model.Usuarios;

/**
 *
 * @author Rafael
 */
public class UsuariosDAO {
    
    private final Connection connection;
    
    //CONSTRUTOR
    public UsuariosDAO(Connection connection) {
        this.connection = connection;
    }

    public UsuariosDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean existeUsuario(Usuarios usuario) throws SQLException{

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        boolean retorno = false;
        
        try {
        
        String sql = "select * from usuarios "
                + "where "
                + "usuario = ? and senha = ? ";
        
 
        pst = con.prepareStatement(sql);
        pst.setString(1, usuario.getUsuario()); 
        pst.setString(2, usuario.getSenha());
        
        pst.execute();
        
        rs = pst.getResultSet();
        
        if(rs.next() == true){
            
            retorno = true;
            
        } else {
            
            retorno = false;
            
        }
        
        } catch (SQLException ex) {
            
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método existeUsuario() na classe UsuariosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, pst, rs);
            
	}
        
        return retorno;
        
    }
    
    public Usuarios CadastrarUsuario (Usuarios usuario)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement pst = null;
        
        String sql = "INSERT INTO usuarios(\n" +
        "	usuario, senha, permissao)\n" +
        "	VALUES (?, ?, ?);";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getSenha());
            pst.setInt(3, usuario.getPermissao());
            
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método CadastrarUsuario() na classe UsuariosDAO");
            
            
        } 
        
        return usuario;
    
    }

    public void AtualizarUsuario (Usuarios atualizausuario) {
               
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        String sql = "UPDATE usuarios "
                + "SET usuario = ?, senha = ?, permissao = ? "
                + "WHERE codusuario = ?";
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, atualizausuario.getUsuario());
            stmt.setString(2, atualizausuario.getSenha());
            stmt.setInt(3, atualizausuario.getPermissao());
            stmt.setInt(4, atualizausuario.getCodusuario());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro - Método AtualizarUsuário() em UsuáriosDAO");
            
        } 
    
    }
    
    public Usuarios ExcluirUsuario(Usuarios excluiusuario) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        String sql = "delete from usuarios where codusuario = ?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, excluiusuario.getCodusuario());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ExcluirUsuario() na classe UsuariosDAO");
            
        } 
        
        return excluiusuario;
    }
    
    public Iterable<Usuarios> ListarUsuarios() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuarios> usuarios = new ArrayList<>();

        String sql = "SELECT codusuario, usuario, senha, permissao\n" +
        "	FROM usuarios;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                usuarios.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarUsuarios() na classe UsuariosDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return usuarios;
    }
    
    public Iterable<Usuarios> ListarUsuariosPorUsuario(String usuario) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuarios> usuarios = new ArrayList<>();

        String sql = "select codusuario, usuario, senha, permissao "
                + "from usuarios "
                + "where usuario like ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+usuario+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                usuarios.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarUsuariosPorUsuario() na classe UsuariosDao");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return usuarios;
    }
    
    public Usuarios CarregarResultSet1(ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO TODOS OS CAMPOS
        
        Usuarios usuario = new Usuarios();
        
        usuario.setCodusuario(rs.getInt("codusuario"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setPermissao(rs.getInt("permissao"));
        
        return usuario;
        
    }
    
    
}
