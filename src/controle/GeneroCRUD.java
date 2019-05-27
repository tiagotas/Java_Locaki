/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Genero;

/**
 *
 * @author Tiago
 */
public class GeneroCRUD {

    public int idGenero;
    public String descricao;
    public String dataCadastro;
    public String fgAtivo;

    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Genero)
        {
            Genero g = (Genero) o;

            PreparedStatement stmt = conexao.con.prepareStatement("SELECT CADASTRA_GENERO(?)");

            stmt.setString(1, g.getDescricao());

            stmt.execute();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Genero.class);
        }
    }


    public void editar(Object o) throws SQLException
    {
        if(o instanceof Genero)
        {
            Genero g = (Genero) o;

            PreparedStatement stmt = conexao.con.prepareStatement("UPDATE genero SET descricao = ? WHERE id_genero = ?");

            stmt.setString(1, g.getDescricao());
            stmt.setInt(2, g.getIdGenero());

            stmt.execute();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Genero.class);
        }
    }


    public void excluir(int idPromocao) throws SQLException
    {

        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE promocoes SET ativo='N' WHERE id_promocao = ?");

        stmt.setInt(1, idPromocao);

        stmt.execute();
        stmt.close();
    }


    public List lista(String q) throws SQLException
    {
        List<Genero> lista = new ArrayList<Genero>();

      // if(q.isEmpty())
       //{
            PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_generos WHERE ativo = 'S' ORDER BY descricao ASC");
       //} else {
            //PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_generos WHERE descricao LIKE '%%" + q + "%%' ORDER BY id_promocao DESC ");
       //}

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Genero g = new Genero();

            g.setIdGenero(rs.getInt("id_genero"));
            g.setDescricao(rs.getString("descricao"));
            g.setDataCadastro(rs.getDate("data_cadastro"));

            lista.add(g);
        }

        stmt.close();

        return lista;
    }
}