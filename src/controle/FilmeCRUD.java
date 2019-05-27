/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import modelo.*;
import modelo.Filme;

/**
 *
 * @author Tiago
 */
public class FilmeCRUD {

     Conexao conexao = new Conexao();

    public void cadastrar(Filme f) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List pesqF(String retorno) throws SQLException {

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
