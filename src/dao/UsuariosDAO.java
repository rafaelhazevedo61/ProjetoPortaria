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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
        } finally {
            
            ConnectionFactory.closeConnection(con, pst, rs);
            
	}
        
        return retorno;
        
    }
    
    
}
