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
import modelo.Locacao;

/**
 *
 * @author Tiago
 */
public class LocacaoCRUD {
    
    Conexao conexao = new Conexao();

    List<Locacao> lista = new ArrayList<Locacao>();

    public List lista(String retorno) throws SQLException {

        

        PreparedStatement stmt = conexao.con.prepareStatement(
                "SELECT * FROM itens_locacao " +
                "WHERE nome = '" +retorno+ "'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Locacao l = new Locacao();

            l.setlCod(rs.getInt("id_filme"));
            l.setlTitulo(rs.getString("nome"));
            //l.setlQuant(rs.getString("cor"));
            l.setlGenero(rs.getString("descricao"));

            lista.add(l);

        }

        stmt.close();

        return lista;

    }

}
