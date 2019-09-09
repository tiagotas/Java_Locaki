/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle.relatorios;

import controle.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.RelatoriosGerais;

/**
 *
 * @author Tiago
 */
public class RelatorioCRUD
{
     Conexao conexao = new Conexao();

    public List getFilmesTopDez() throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM filmes_top_dez ");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdFilme(rs.getInt("id_filme"));
            r.setFilme(rs.getString("nome"));
            r.setTotalLocacoes(rs.getInt("nro_locacoes"));
            r.setReceita(rs.getDouble("receita"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getFilmesMaisProcurados(String filme) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM filmes_mais_procurados WHERE nome LIKE '%" + filme + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdFilme(rs.getInt("id_filme"));
            r.setFilme(rs.getString("nome"));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setUltimaLocacao(rs.getTimestamp("ultima_locacao"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getFilmesMenosProcurados(String filme) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM filmes_menos_procurados WHERE nome LIKE '%" + filme + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdFilme(rs.getInt("id_filme"));
            r.setFilme(rs.getString("nome"));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setUltimaLocacao(rs.getTimestamp("ultima_locacao"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getClientesComFilmes(String nome) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM clientes_com_filmes WHERE nome LIKE '%" + nome + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdCliente(rs.getInt("id_cliente"));
            r.setCliente(rs.getString("nome"));
            r.setUltimaLocacao(rs.getTimestamp("data_ultima_locacao"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getClientesMaisAtivos(String nome) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM clientes_mais_ativos WHERE nome LIKE '%" + nome + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdCliente(rs.getInt("id_cliente"));
            r.setCliente(rs.getString("nome"));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setUltimaLocacao(rs.getTimestamp("ultima_locacao"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getClientesMenosAtivos(String nome) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM clientes_menos_ativos WHERE nome LIKE '%" + nome + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdCliente(rs.getInt("id_cliente"));
            r.setCliente(rs.getString("nome"));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setUltimaLocacao(rs.getTimestamp("ultima_locacao"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getLocacoesAtrasadas(String filme) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM filmes_devolucoes_atrasadas  WHERE nome LIKE '%" + filme + "%'");;

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdFilme(rs.getInt("id_filme"));
            r.setFilme(rs.getString("nome"));
            r.setDataDevolucaoPrevista(rs.getDate("data_devolucao_estimada"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getLocacoesDevolvidosHoje(String filme) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM filmes_devolvidos_hoje  WHERE nome LIKE '%" + filme + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdFilme(rs.getInt("id_filme"));
            r.setFilme(rs.getString("nome"));
            r.setDataDevolucaoReal(rs.getTimestamp("data_devolucao_real"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public RelatoriosGerais getTotalLocacoesMes() throws SQLException
    {
        RelatoriosGerais r = null;

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM TOTAL_LOCACOES_MES ");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            r = new RelatoriosGerais();

            r.setTotalLocacoes(rs.getInt("TOTAL_LOCACOES"));
            r.setReceita(rs.getDouble("RECEITA_TOTAL"));
        }

        stmt.close();

        return r;
    }

    public List getReceitaPorFilme(String filme) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM receita_por_filme WHERE nome LIKE '%" + filme + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdFilme(rs.getInt("id_filme"));
            r.setFilme(rs.getString("nome"));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setReceita(rs.getDouble("receita_filme"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }


    public List getReceitaPorGenero(String genero) throws SQLException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM receita_por_genero WHERE descricao LIKE '%" + genero + "%'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();

            r.setIdGenero(rs.getInt("id_genero"));
            r.setGenero(rs.getString("descricao"));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setReceita(rs.getDouble("receita_genero"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }

    public List getReceitaPorPeriodo(Date dataInicial, Date dataFinal) throws SQLException, ParseException
    {

        List<RelatoriosGerais> lista = new ArrayList<RelatoriosGerais>();

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM receita_por_periodo WHERE to_date(DATA, 'DD/MM/YYYY') BETWEEN ? AND ? ");

        stmt.setDate(1, dataInicial);
        stmt.setDate(2, dataFinal);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            RelatoriosGerais r = new RelatoriosGerais();



            r.setPeriodo(new java.sql.Date( ((java.util.Date)formatador.parse(rs.getString("data"))).getTime()));
            r.setTotalLocacoes(rs.getInt("total_locacoes"));
            r.setReceita(rs.getDouble("receita_periodo"));

            lista.add(r);
        }

        stmt.close();

        return lista;
    }
}
