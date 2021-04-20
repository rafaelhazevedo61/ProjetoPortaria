/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/ProjetoBarbearia";
    private static final String USER = "postgres";
    private static final String PASS = "arisco2017";

    public static Connection getConnection() {
        try {
            
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            throw new RuntimeException("Erro na conexão: ", ex);

        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro no método closeConnection()-1 na classe ConnectionFactory");
                
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro no método closeConnection()-2 na classe ConnectionFactory");
                
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Erro no método closeConnection()-3 na classe ConnectionFactory");
                    
        }
    }

    //CONFIRMAR SE NÃO PRECISA E DEPOIS TIRAR
//        /**
//     * Obtém uma conexão para a base de dados sakila.
//     *
//     * @return Uma conexão para a base de dados sakila.
//     * @throws SQLException Caso ocorra algum problema durante a conexão.
//     */
//    public static Connection getSakilaConnection() throws SQLException {
// 
//        return getConnection();
// 
//    }
    
}
