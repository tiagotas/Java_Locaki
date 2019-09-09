/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import modelo.Dicas;
import modelo.Telefone;

/**
 *
 * @author Tiago
 */
public class DicasCRUD
{
    Conexao conexao = new Conexao();

    

    public Telefone getTelefone(int idTelefone) throws SQLException
    {
        Telefone t = null;

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT * FROM relacao_telefones_cliente WHERE id_telefone = ?");

         pstmt.setInt(1, idTelefone);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             t = new Telefone();

             t.setIdTelefone(rs.getInt("id_telefone"));
             t.setTipoTelefone(rs.getString("tipo_telefone"));
             t.setNumero(rs.getString("numero"));
             t.setObservacoes(rs.getString("observacoes"));
         }

        rs.close();
        pstmt.close();

        return t;
    }

    public List listar()  throws SQLException
    {
      //  DefaultListModel lista = new DefaultListModel();
        List<Dicas> lista = new ArrayList<Dicas>();
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT titulo FROM dicas");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Dicas d = new Dicas();

             d.setTitulo(rs.getString("titulo"));
            
             lista.add(d);
        }

        stmt.close();

        return lista;
    }

    public List listarDescricao()  throws SQLException
    {

        List<Dicas> lista = new ArrayList<Dicas>();
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT descricao FROM dicas WHERE");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Dicas d = new Dicas();

             d.setTitulo(rs.getString("titulo"));

             lista.add(d);
        }

        stmt.close();

        return lista;
    }

}