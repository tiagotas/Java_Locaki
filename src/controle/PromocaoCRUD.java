/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Promocao;

/**
 *
 * @author Tiago
 */
public class PromocaoCRUD
{
    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Promocao)
        {
            Promocao p = (Promocao) o;

            PreparedStatement stmt = conexao.con.prepareStatement("SELECT CADASTRA_PROMOCAO(?, ?)");

            stmt.setString(1, p.getDescricao().toUpperCase());
            stmt.setDouble(2, p.getCoeficienteDesconto());

            stmt.execute();
            stmt.close();
        } else
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Promocao.class);
    }

    public void editar(Object o) throws SQLException
    {
        if(o instanceof Promocao)
        {
            Promocao p = (Promocao) o;

            PreparedStatement stmt = conexao.con.prepareStatement("UPDATE promocoes SET descricao=?, coeficiente_desconto=? WHERE id_promocao = ? AND ativo = 'S'");

            stmt.setString(1, p.getDescricao().toUpperCase());
            stmt.setDouble(2, p.getCoeficienteDesconto());
            stmt.setInt(3, p.getIdPromocao());

            stmt.execute();
            stmt.close();
        } else
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Promocao.class);
    }

    public Promocao getRegistro(int idPromocao) throws SQLException
    {
        Promocao p = null;
        
        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT * FROM relacao_promocoes WHERE ID_PROMOCAO = ? AND ativo = 'S'");

        pstmt.setInt(1, idPromocao);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             p = new Promocao();
             p.setIdPromocao(rs.getInt("id_Promocao"));
             p.setDataCadastro(rs.getTimestamp("data_cadastro"));
             p.setDescricao(rs.getString("descricao"));
             p.setCoeficienteDesconto(rs.getDouble("coeficiente_desconto"));
         }

        rs.close();
        pstmt.close();

        return p;
    }


    public void excluir(int idPromocao) throws SQLException
    {
        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE promocoes SET ativo='N' WHERE id_promocao = ?");

        stmt.setInt(1, idPromocao);

        stmt.execute();
        stmt.close();
    }

    public List buscar(String q) throws SQLException
    {
        List<Promocao> lista = new ArrayList<Promocao>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_promocoes WHERE ativo='S' AND UPPER(descricao) LIKE '" + q.toUpperCase() + "%%' ORDER BY id_promocao DESC ");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Promocao p = new Promocao();

            p.setIdPromocao(rs.getInt("id_promocao"));
            p.setDescricao(rs.getString("descricao"));
            p.setCoeficienteDesconto(rs.getDouble("coeficiente_desconto"));
            p.setDataCadastro(rs.getTimestamp("data_cadastro"));

            lista.add(p);
        }

        stmt.close();

        return lista;
    }

    public List lista() throws SQLException
    {
        List<Promocao> lista = new ArrayList<Promocao>();
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_promocoes WHERE ativo='S' ORDER BY id_promocao DESC ");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Promocao p = new Promocao();

            p.setIdPromocao(rs.getInt("id_promocao"));
            p.setDescricao(rs.getString("descricao"));
            p.setCoeficienteDesconto(rs.getDouble("coeficiente_desconto"));
            p.setDataCadastro(rs.getTimestamp("data_cadastro"));


            lista.add(p);
        }

        stmt.close();

        return lista;
    }
}