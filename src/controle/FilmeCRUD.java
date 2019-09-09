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
import modelo.Filme;

/**
 *
 * @author Tiago
 */
public class FilmeCRUD
{
    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Filme)
        {
            Filme f = (Filme) o;

            String sql = "BEGIN; ";
                   sql += "INSERT INTO filmes (nome, unidades, valor, dias_devolucao, lancamento) VALUES ('"+ f.getTitulo() + "', "+ f.getUnidades() + ", " + f.getValor() + ", " + f.getDiasDevolucao() + ", '" + f.getLancamento() + "'); ";
                   
                   
            for(int i=0; i < Filme.getGeneros().toArray().length; i++)
            {
                PreparedStatement stmt = conexao.con.prepareStatement("SELECT id_genero FROM generos WHERE descricao = ?");
                
                stmt.setString(1, String.valueOf(Filme.getGeneros().toArray()[i]));
                
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next())
                   sql += "INSERT INTO filme_generos (id_filme, id_genero) VALUES ((SELECT CURRVAL('filmes_id_filme_seq')), "+ rs.getInt("id_genero") +"); ";
            }
                   
                   sql += "COMMIT; ";
                   
            PreparedStatement stmt = conexao.con.prepareStatement(sql);

            stmt.execute();
            stmt.close();
        } else
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Filme.class);
    }

    public void editar(Object o) throws SQLException
    {
        if(o instanceof Filme)
        {
            Filme f = (Filme) o;

            String sql  = "BEGIN; ";
                   sql += "UPDATE filmes SET nome='" + f.getTitulo() + "', unidades=" + f.getUnidades() +", valor=" + f.getValor() + ", lancamento='" + f.getLancamento() + "', dias_devolucao=" + f.getDiasDevolucao() + " WHERE id_filme = " + f.getIdFilme() + ";";
                   sql += "DELETE FROM filme_generos WHERE id_filme = " + f.getIdFilme() + ";";

                   for(int i=0; i < Filme.getGeneros().toArray().length; i++)
                   {
                       PreparedStatement stmt = conexao.con.prepareStatement("SELECT id_genero FROM generos WHERE descricao = ?");

                       stmt.setString(1, String.valueOf(Filme.getGeneros().toArray()[i]));

                       ResultSet rs = stmt.executeQuery();

                       while (rs.next())
                          sql += "INSERT INTO filme_generos (id_filme, id_genero) VALUES ("+ f.getIdFilme() +", "+ rs.getInt("id_genero") +"); ";
                    }

                   sql += "COMMIT; ";

            PreparedStatement stmt = conexao.con.prepareStatement(sql);

            stmt.execute();
            stmt.close();
        } else
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Filme.class);
    }


    public void excluir(int idFilme) throws SQLException
    {
        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE filmes SET ativo='N' WHERE id_filme = ?");

        stmt.setInt(1, idFilme);

        stmt.execute();
        stmt.close();
    }

    public List lista() throws SQLException
    {
         List<Filme> lista = new ArrayList<Filme>();

         PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_filmes WHERE ativo='S' ORDER BY RANDOM() LIMIT 50 ");

         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {

            Filme f = new Filme();

            f.setIdFilme(rs.getInt("id_filme"));
            f.setTitulo(rs.getString("nome"));
            f.setUnidades(rs.getInt("unidades"));
            f.setDiasDevolucao(rs.getInt("dias_devolucao"));
            f.setLancamento(rs.getString("lancamento"));
            f.setValor(rs.getDouble("valor"));
            f.setDataCadastro(rs.getTimestamp("data_cadastro"));

            lista.add(f);
        }

        stmt.close();

        return lista;
    }

    public List buscar(String q) throws SQLException
    {
         List<Filme> lista = new ArrayList<Filme>();

         PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_filmes WHERE nome LIKE '%%" + q.toUpperCase() + "%%' AND ativo='S'");

         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {

            Filme f = new Filme();

            f.setIdFilme(rs.getInt("id_filme"));
            f.setTitulo(rs.getString("nome"));
            f.setUnidades(rs.getInt("unidades"));
            f.setDataCadastro(rs.getTimestamp("data_cadastro"));

            lista.add(f);
        }

        stmt.close();

        return lista;
    }

    public Filme getRegistro(int idFilme) throws SQLException
    {
        Filme f = null;

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT * FROM relacao_filmes WHERE id_filme = ? AND ativo = 'S'");

        pstmt.setInt(1, idFilme);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             f = new Filme();

             f.setIdFilme(rs.getInt("id_filme"));
             f.setDataCadastro(rs.getTimestamp("data_cadastro"));
             f.setTitulo(rs.getString("nome"));
             f.setDiasDevolucao(rs.getInt("dias_devolucao"));
             f.setLancamento(rs.getString("lancamento"));
             f.setValor(rs.getDouble("valor"));
             f.setUnidades(rs.getInt("unidades"));
         }

        rs.close();
        pstmt.close();

        return f;
    }

    public DefaultListModel listarGenerosGerais(int idFilme) throws SQLException
    {
        DefaultListModel lista = new DefaultListModel();

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT descricao FROM generos g WHERE g.id_genero NOT IN (SELECT id_genero FROM filme_generos WHERE id_genero = g.id_genero AND id_filme= ?)");

        pstmt.setInt(1, idFilme);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
            lista.addElement(rs.getString("descricao"));

        rs.close();
        pstmt.close();

        return lista;
    }

    public DefaultListModel listarGenerosFilme(int idFilme) throws SQLException
    {
        DefaultListModel lista = new DefaultListModel();

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT descricao FROM generos JOIN filme_generos USING(id_genero) WHERE id_filme = ?");

        pstmt.setInt(1, idFilme);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
            lista.addElement(rs.getString("descricao"));

        rs.close();
        pstmt.close();

        return lista;
    }
}