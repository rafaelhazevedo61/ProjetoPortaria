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
import model.UsuariosPermissao;

/**
 *
 * @author Rafael
 */
public class UsuariosPermissaoDAO {
            
    private final Connection connection;
    
    //CONSTRUTOR
    public UsuariosPermissaoDAO(Connection connection) {
        this.connection = connection;
        
    }
    
    public Iterable<UsuariosPermissao> ListarPermissoesJComboBox() {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<UsuariosPermissao> permissoes = new ArrayList<>();

        String sql = "SELECT descricao\n" +
        "	FROM usuarios_permissao;";
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                permissoes.add(CarregarResultSet1(rs));
                
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(UsuariosPermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro no método ListarPermissoesJComboBox() na classe PermissoesDAO");
            
        } finally {
            
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

        return permissoes;
    }
    
    public UsuariosPermissao CarregarResultSet1 (ResultSet rs) throws SQLException {
        
        //RESULTSET CONTENDO O CAMPO NOME
        
        UsuariosPermissao permissao = new UsuariosPermissao();
        
        permissao.setDescricao(rs.getString("descricao"));
        
        return permissao;
        
    }
    
}
