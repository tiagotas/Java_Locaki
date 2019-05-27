/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiago
 */
public class Conexao {

    private static String host = "localhost";
    private static String database = "locaki";
    private static String usuario = "postgres";
    private static String senha = "";
    private static String url = "jdbc:postgresql://" + host + "/" + database;

    public Connection con;

    public Conexao()
    {
        try
        {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(url, usuario, senha);

        } catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }


    public void fecharConexao()
    {
        try
        {
            con.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

}
