/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Filme;
import modelo.Genero;

/**
 *
 * @author Tiago
 */
public class GeneroCRUD {

    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Genero)
        {
            Genero g = (Genero) o;

            PreparedStatement stmt = conexao.con.prepareStatement("SELECT CADASTRA_GENERO(?)");

            stmt.setString(1, g.getDescricao().toUpperCase());

            stmt.execute();
            stmt.close();
        } else
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Genero.class);
    }


    public void editar(Object o) throws SQLException
    {
        if(o instanceof Genero)
        {
            Genero g = (Genero) o;

            PreparedStatement stmt = conexao.con.prepareStatement("UPDATE generos SET descricao = ? WHERE id_genero = ?");

            stmt.setString(1, g.getDescricao().toUpperCase());
            stmt.setInt(2, g.getIdGenero());

            stmt.execute();
            stmt.close();
        } else
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Genero.class);
    }

    public Genero getRegistro(int idGenero) throws SQLException
    {
        Genero g = null;

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT * FROM relacao_generos WHERE id_genero = ? AND ativo = 'S'");

        pstmt.setInt(1, idGenero);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             g = new Genero();
             g.setIdGenero(rs.getInt("id_genero"));
             g.setDataCadastro(rs.getTimestamp("data_cadastro"));
             g.setDescricao(rs.getString("descricao"));
         }

        rs.close();
        pstmt.close();

        return g;
    }


    public void excluir(int idGenero) throws SQLException
    {

        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE generos SET ativo='N' WHERE id_genero = ?");

        stmt.setInt(1, idGenero);

        stmt.execute();
        stmt.close();
    }


    public List buscar(String q) throws SQLException
    {
        List<Genero> lista = new ArrayList<Genero>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_generos WHERE ativo='S' AND UPPER(descricao) LIKE '%%" + q.toUpperCase() + "%%' ORDER BY id_genero DESC ");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Genero g = new Genero();

            g.setIdGenero(rs.getInt("id_genero"));
            g.setDataCadastro(rs.getTimestamp("data_cadastro"));
            g.setDescricao(rs.getString("descricao"));

            lista.add(g);
        }

        stmt.close();

        return lista;
    }


     public List lista() throws SQLException
    {
        List<Genero> lista = new ArrayList<Genero>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_generos WHERE ativo = 'S' ORDER BY descricao ASC");


        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Genero g = new Genero();

            g.setIdGenero(rs.getInt("id_genero"));
            g.setDescricao(rs.getString("descricao"));
            g.setDataCadastro(rs.getTimestamp("data_cadastro"));

            lista.add(g);
        }

        stmt.close();

        return lista;
    }
}