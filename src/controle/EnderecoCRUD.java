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
import modelo.Endereco;

/**
 *
 * @author Tiago
 */
public class EnderecoCRUD
{
    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Endereco)
        {
            Endereco e = (Endereco) o;

            String sql  = " BEGIN; ";
                   sql += " INSERT INTO enderecos (lagradouro, numero, bairro, cep, cidade, estado) VALUES (?,?,?,?,?,?); ";
                   sql += " INSERT INTO cliente_enderecos (id_cliente, id_endereco) VALUES (?, (SELECT CURRVAL('enderecos_id_endereco_seq'))); ";
                   sql += " COMMIT; ";

            PreparedStatement stmt = conexao.con.prepareStatement(sql);
            

            stmt.setString(1, e.getLagradouro().toUpperCase());
            stmt.setString(2, e.getNumero().toUpperCase());
            stmt.setString(3, e.getBairro().toUpperCase());
            stmt.setString(4, e.getCep());
            stmt.setString(5, e.getCidade().toUpperCase());
            stmt.setString(6, e.getEstado().toUpperCase());
            stmt.setInt(7, e.getIdCliente());


            stmt.execute();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Endereco.class);
        }
    }

    public void editar(Object o) throws SQLException
    {
        if(o instanceof Endereco)
        {
            Endereco e = (Endereco) o;

            PreparedStatement stmt = conexao.con.prepareStatement("UPDATE enderecos SET lagradouro=?, numero=?, bairro=?, cep=?, cidade=?, estado=? WHERE id_endereco=?");

            stmt.setString(1, e.getLagradouro().toUpperCase());
            stmt.setString(2, e.getNumero().toUpperCase());
            stmt.setString(3, e.getBairro().toUpperCase());
            stmt.setString(4, e.getCep().replaceAll("[/./[-]/]", ""));
            stmt.setString(5, e.getCidade().toUpperCase());
            stmt.setString(6, e.getEstado().toUpperCase());
            stmt.setInt(7, e.getIdEndereco());

            stmt.executeUpdate();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Endereco.class);
        }
    }

    public void excluir(int idEndereco) throws SQLException
    {
        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE enderecos SET ativo='N' WHERE id_endereco = ?");

        stmt.setInt(1, idEndereco);

        stmt.execute();
        stmt.close();
    }

    public Endereco getEndereco(int idEndereco) throws SQLException
    {
        Endereco e = null;

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT * FROM relacao_enderecos_cliente WHERE id_endereco = ?");

         pstmt.setInt(1, idEndereco);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             e = new Endereco();

             e.setIdEndereco(rs.getInt("id_endereco"));
             e.setLagradouro(rs.getString("lagradouro"));
             e.setNumero(rs.getString("numero"));
             e.setBairro(rs.getString("bairro"));
             e.setCep(rs.getString("cep"));
             e.setCidade(rs.getString("cidade"));
             e.setEstado(rs.getString("estado"));
         }

        rs.close();
        pstmt.close();

        return e;
    }

    public List listar(int idCliente)  throws SQLException
    {

        List<Endereco> lista = new ArrayList<Endereco>();
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_enderecos_cliente WHERE id_cliente = ? AND ativo='S' ");

        stmt.setInt(1, idCliente);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Endereco e = new Endereco();

             e.setIdEndereco(rs.getInt("id_endereco"));
             e.setLagradouro(rs.getString("lagradouro"));
             e.setNumero(rs.getString("numero"));
             e.setBairro(rs.getString("bairro"));
             e.setCep(rs.getString("cep"));
             e.setCidade(rs.getString("cidade"));
             e.setEstado(rs.getString("estado"));
             e.setDataCadastro(rs.getTimestamp("data_cadastro"));

            lista.add(e);
        }

        stmt.close();

        return lista;
    }

}
