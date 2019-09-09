/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import controle.arrays.LocacaoArrayCrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Filme;
import modelo.Locacao;

/**
 *
 * @author Tiago
 */
public class LocacaoCRUD {
    
    Conexao conexao = new Conexao();

    ArrayList<Filme> lista = new ArrayList<Filme>();

    public void add(String promocao, String titulo) throws SQLException
    {
        LocacaoArrayCrud lCrud = new LocacaoArrayCrud();

        String sql = "SELECT f.id_filme, f.nome, f.dias_devolucao, "
                   + "f.valor * (SELECT coeficiente_desconto FROM PROMOCOES WHERE descricao= ?) AS valor "
                   + "FROM filmes f WHERE nome = ?";

        PreparedStatement stmt = conexao.con.prepareStatement(sql);

        stmt.setString(1, promocao);
        stmt.setString(2, titulo);

        ResultSet rs = stmt.executeQuery();

        while(rs.next())
        {
            Filme f = new Filme();

            f.setIdFilme(rs.getInt("id_filme"));
            f.setTitulo(rs.getString("nome"));
            f.setValor(rs.getDouble("valor"));
            f.setDiasDevolucao(rs.getInt("dias_devolucao"));

            lCrud.add(f);
        }
    }


    public double buildTotalLocacao()
    {
        LocacaoArrayCrud lCrud = new LocacaoArrayCrud();

        ArrayList<Filme> filmes = (ArrayList<Filme>) lCrud.listar();

        double total = 0;

        for(int i=0; i < filmes.size(); i++)
            total += filmes.get(i).getValor();

        return total;
    }

    public Date buildDataDevolucao()
    {
        LocacaoArrayCrud lCrud = new LocacaoArrayCrud();
        Calendar calendar = Calendar.getInstance();

        ArrayList<Filme> filmes = (ArrayList<Filme>) lCrud.listar();
        int menorDia = 0;

        calendar.setTime(new Date());

        for(int i=0; i < filmes.size(); i++)
        {
            if(i == 0)
                menorDia = filmes.get(i).getDiasDevolucao();

            if(filmes.get(i).getDiasDevolucao() < menorDia)
                menorDia = filmes.get(i).getDiasDevolucao();
        }

        calendar.add(Calendar.DATE, menorDia);

        if(calendar.get(Calendar.DAY_OF_WEEK) == 1) // verifica se via cair num domingo
            calendar.add(Calendar.DATE, 1);

        return calendar.getTime();
    }


    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Locacao)
        {
            Locacao f = (Locacao) o;

            lista = f.getItensLocacao();

            String sql  = "BEGIN; ";
                   sql += "INSERT INTO locacoes (id_cliente, id_promocao, valor_locacao, data_devolucao_estimada) ";
                   sql += "VALUES ";
                   sql += "((SELECT id_cliente FROM clientes WHERE nome = '" + f.getCliente() + "'), (SELECT id_promocao FROM promocoes WHERE descricao = '" + f.getPromocao() + "'), " + f.getValorLocacao() + ", '" + f.getDataDevolucaoEstimada() + "'); ";

                   System.out.println(f.getDataDevolucaoEstimada());

                   for(int i=0; i < lista.size(); i++)
                      sql +=  "INSERT INTO locacoes_itens (id_locacao, id_filme) VALUES ((SELECT CURRVAL('locacoes_id_locacao_seq')), " + lista.get(i).getIdFilme() + "); ";

                   sql += "COMMIT; ";
            
            PreparedStatement stmt = conexao.con.prepareStatement(sql);

            stmt.execute();
            stmt.close();

            LocacaoArrayCrud lCrud = new LocacaoArrayCrud();

            lCrud.removeAll(); //limpa o array de itens apos inserção

        } else
           throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Locacao.class);
    }


    public void excluir(int idLocacao)  throws SQLException
    {
        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE locacoes SET ativo='N' WHERE id_locacao = ?");

        stmt.setInt(1, idLocacao);

        stmt.execute();
        stmt.close();
    }

    public void baixar(int idLocacao)  throws SQLException
    {
        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE locacoes SET data_devolucao_real = NOW() WHERE id_locacao = ?");

        stmt.setInt(1, idLocacao);

        stmt.execute();
        stmt.close();
    }


    public List buscar(String q) throws SQLException
    {
        List<Locacao> lista = new ArrayList<Locacao>();


        String sql  = "SELECT l.id_locacao, l.valor_locacao, l.data_locacao, c.nome, p.descricao "
                    + "FROM locacoes l "
                    + "JOIN clientes c  ON (c.id_cliente = l.id_cliente) "
                    + "JOIN promocoes p ON (p.id_promocao = l.id_promocao) "
                    + "JOIN locacoes_itens li ON (li.id_locacao = l.id_locacao) "
                    + "JOIN filmes f ON (f.id_filme = li.id_filme) "
                    + "WHERE c.nome LIKE '%" + q + "%' OR f.nome LIKE '%" + q + "%' "
                    + "GROUP BY l.id_locacao, l.valor_locacao, l.data_locacao, c.nome, p.descricao ";

        PreparedStatement stmt = conexao.con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            Locacao l = new Locacao();

            l.setIdLocacao(rs.getInt("id_locacao"));
            l.setCliente(rs.getString("nome"));
            l.setValorLocacao(rs.getDouble("valor_locacao"));
            l.setDataLocacao(rs.getTimestamp("data_locacao"));

            lista.add(l);
        }

        return lista;
    }

    public Locacao getRegistro(int idLocacao) throws SQLException
    {
        Locacao l = null;

        String sql  = "SELECT l.id_locacao, l.valor_locacao, l.data_locacao, l.data_devolucao_estimada, l.data_devolucao_real, c.nome, p.descricao "
                    + "FROM locacoes l "
                    + "JOIN clientes c  ON (c.id_cliente = l.id_cliente) "
                    + "JOIN promocoes p ON (p.id_promocao = l.id_promocao) "
                    + "WHERE l.id_locacao = ? ";

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement(sql);

        pstmt.setInt(1, idLocacao);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             l = new Locacao();

             l.setIdLocacao(rs.getInt("id_locacao"));
             l.setDataDevolucaoEstimada(rs.getDate("data_devolucao_estimada"));
             l.setDataDevolucaoReal(rs.getTimestamp("data_devolucao_real"));
             l.setDataLocacao(rs.getTimestamp("data_locacao"));
             l.setValorLocacao(rs.getDouble("valor_locacao"));
             l.setCliente(rs.getString("nome"));
             l.setPromocao(rs.getString("descricao"));
         }

        rs.close();
        pstmt.close();

        return l;
    }

    public List itensLocacao(int idLocacao) throws SQLException
    {
        List<Filme> lista = new ArrayList<Filme>();

        String sql  = "SELECT f.id_filme, f.nome, f.valor * (SELECT coeficiente_desconto  "
                    + "					     FROM locacoes "
                    + "					     JOIN promocoes USING (id_promocao) "
                    + "					     WHERE id_locacao = l.id_locacao) AS valor_final "
                    + "FROM locacoes l "
                    + "JOIN locacoes_itens li ON (li.id_locacao = l.id_locacao) "
                    + "JOIN filmes f ON (f.id_filme = li.id_filme) "
                    + "WHERE l.id_locacao = ? "
                    + "ORDER BY f.id_filme ASC ";

        PreparedStatement stmt = conexao.con.prepareStatement(sql);

        stmt.setInt(1, idLocacao);

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            Filme f = new Filme();

            f.setIdFilme(rs.getInt("id_filme"));
            f.setTitulo(rs.getString("nome"));
            f.setValor(rs.getDouble("valor_final"));

            lista.add(f);
        }

        return lista;
    }

    public List lista(String retorno) throws SQLException {
        

        PreparedStatement stmt = conexao.con.prepareStatement(
                "SELECT * FROM itens_locacao " +
                "WHERE nome = '" + retorno + "'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            Locacao l = new Locacao();

            //l.setlCod(rs.getInt("id_filme"));
            //l.setlTitulo(rs.getString("nome"));
            //l.setlQuant(rs.getString("cor"));
            //l.setlGenero(rs.getString("descricao"));

            //lista.add(l);

        }

        stmt.close();

        return lista;

    }

    public List pesquisarPromocao(String retorno) throws SQLException {

        List<String> lista = new ArrayList<String>();

        PreparedStatement stmt = conexao.con.prepareStatement(
                "SELECT descricao FROM promocoes " +
                "WHERE descricao like '" +retorno+ "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();
            String re = rs.getString("descricao");

            lista.add(re);

        }

        stmt.close();

        return lista;

    }

    public List pesquisarCliente(String retorno) throws SQLException {

        List<String> lista = new ArrayList<String>();

        PreparedStatement stmt = conexao.con.prepareStatement(
                "SELECT nome FROM clientes " +
                "WHERE nome like '" +retorno+ "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();
            String re = rs.getString("nome");

            lista.add(re);

        }

        stmt.close();

        return lista;

    }

    public List pesquisarFilme(String retorno) throws SQLException {

        List<String> lista = new ArrayList<String>();

        PreparedStatement stmt = conexao.con.prepareStatement(
                "SELECT nome FROM filmes " +
                "WHERE nome like '" +retorno+ "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();
            String re = rs.getString("nome");

            lista.add(re);

        }

        stmt.close();

        return lista;

    }
}
