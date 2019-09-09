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
import modelo.Telefone;

/**
 *
 * @author Tiago
 */
public class TelefoneCRUD
{
    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Telefone)
        {
            Telefone t = (Telefone) o;

            String sql  = " BEGIN; ";
                   sql += " INSERT INTO telefones (tipo_telefone, numero, observacoes) VALUES (?,?,?); ";
                   sql += " INSERT INTO cliente_telefones (id_cliente, id_telefone) VALUES (?, (SELECT CURRVAL('telefones_id_telefone_seq'))); ";
                   sql += " COMMIT; ";

            PreparedStatement stmt = conexao.con.prepareStatement(sql);


            stmt.setString(1, t.getTipoTelefone());
            stmt.setString(2, t.getNumero().replaceAll("[/(/)/ /[-]/]", ""));
            stmt.setString(3, t.getObservacoes().toUpperCase());
            stmt.setInt(4, t.getIdCliente());

            stmt.execute();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Telefone.class);
        }
    }

    public void editar(Object o) throws SQLException
    {
        if(o instanceof Telefone)
        {
            Telefone t = (Telefone) o;

            PreparedStatement stmt = conexao.con.prepareStatement("UPDATE telefones SET tipo_telefone=?, numero=?, observacoes=? WHERE id_telefone=?");

            stmt.setString(1, t.getTipoTelefone());
            stmt.setString(2, t.getNumero().replaceAll("[/(/)/ /[-]/]", ""));
            stmt.setString(3, t.getObservacoes().toUpperCase());
            stmt.setInt(4, t.getIdTelefone());

            stmt.executeUpdate();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Telefone.class);
        }
    }

    public void excluir(int idTelefone) throws SQLException
    {
        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE telefones SET ativo='N' WHERE id_telefone = ?");

        stmt.setInt(1, idTelefone);

        stmt.execute();
        stmt.close();
    }

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

    public List listar(int idCliente)  throws SQLException
    {

        List<Telefone> lista = new ArrayList<Telefone>();
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_telefones_cliente WHERE id_cliente = ? AND ativo='S' ");

        stmt.setInt(1, idCliente);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Telefone t = new Telefone();

             t.setIdTelefone(rs.getInt("id_telefone"));
             t.setTipoTelefone(rs.getString("tipo_telefone"));
             t.setNumero(rs.getString("numero"));
             t.setObservacoes(rs.getString("observacoes"));
             t.setDataCadastro(rs.getTimestamp("data_cadastro"));

            lista.add(t);
        }

        stmt.close();

        return lista;
    }

}