/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.ClienteContadores;
import modelo.ClienteLocacoes;
import modelo.Endereco;
import modelo.Telefone;

/**
 *
 * @author Tiago
 */
public class ClienteCRUD {

    Conexao conexao = new Conexao();

    public void cadastrar(Object o) throws SQLException
    {
        if(o instanceof Cliente)
        {
            Cliente c = (Cliente) o;
            
            ArrayList<Telefone> telefones = c.getTelefones();
            ArrayList<Endereco> enderecos = c.getEnderecos();

            String sql  = "BEGIN; ";

                   sql += "INSERT INTO clientes (nome, cpf, rg, observacoes) VALUES ('" + c.getNome() + "', '" + c.getCpf() + "', '" + c.getRg() + "', '" + c.getObservacoes() + "'); ";

            for(int i=0; i < telefones.size(); i++)
            {
                   sql += "INSERT INTO telefones (numero, tipo_telefone, observacoes) VALUES ('" + telefones.get(i).getNumero() + "', '" + telefones.get(i).getTipoTelefone() + "', '" + telefones.get(i).getObservacoes() + "'); ";
                   sql += "INSERT INTO cliente_telefones (id_cliente, id_telefone) VALUES ((SELECT CURRVAL('clientes_id_cliente_seq')), (SELECT CURRVAL('telefones_id_telefone_seq'))); ";
            }

            for(int i=0; i < enderecos.size(); i++)
            {
                   sql += "INSERT INTO enderecos (lagradouro, numero, bairro, cep, cidade, estado) VALUES ('" + enderecos.get(i).getLagradouro() + "', '" + enderecos.get(i).getNumero() + "', '" + enderecos.get(i).getBairro() + "', '" + enderecos.get(i).getCep() + "', '" + enderecos.get(i).getCidade() + "', '" + enderecos.get(i).getEstado() + "'); ";
                   sql += "INSERT INTO cliente_enderecos (id_cliente, id_endereco) VALUES ((SELECT CURRVAL('clientes_id_cliente_seq')), (SELECT CURRVAL('enderecos_id_endereco_seq'))); ";
            }

                   sql += "COMMIT; ";

            PreparedStatement stmt = conexao.con.prepareStatement(sql);

            stmt.execute();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Cliente.class);
        }
    }


    public void editar(Object o) throws SQLException
    {
        if(o instanceof Cliente)
        {
            Cliente c = (Cliente) o;

            PreparedStatement stmt = conexao.con.prepareStatement("UPDATE clientes SET nome=?, cpf=?, rg=?, observacoes=? WHERE id_cliente=?");
               
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getRg());
            stmt.setString(4, c.getObservacoes());
            stmt.setInt(5, c.getIdCliente());

            stmt.execute();
            stmt.close();
        } else {
            throw new SQLException("Tipo de objeto inesperado. Tipo esperado: \n " + Cliente.class);
        }
    }


    public void excluir(int idCliente) throws SQLException
    {

        PreparedStatement stmt = conexao.con.prepareStatement("UPDATE clientes SET ativo='N' WHERE id_cliente = ?");

        stmt.setInt(1, idCliente);

        stmt.execute();
        stmt.close();
    }


    public List buscar(String q) throws SQLException
    {

        List<Cliente> lista = new ArrayList<Cliente>();
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_clientes WHERE (nome LIKE '%%" + q.toUpperCase() + "%%' OR cpf LIKE '%%" + q.toUpperCase() + "%%') AND ativo='S'");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();

            c.setIdCliente(rs.getInt("id_cliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setDataCadastro(rs.getTimestamp("data_cadastro"));
            lista.add(c);
        }

        stmt.close();

        return lista;
    }



    public List lista() throws SQLException
    {

        List<Cliente> lista = new ArrayList<Cliente>();

        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM relacao_clientes WHERE ativo='S' ORDER BY RANDOM() LIMIT 50");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();

            c.setIdCliente(rs.getInt("id_cliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setDataCadastro(rs.getTimestamp("data_cadastro"));
            
            lista.add(c);
        }

        stmt.close();

        return lista;
    }


    public Cliente getDadosCliente(int idCliente) throws SQLException
    {
        Cliente c = null;

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement("SELECT * FROM relacao_clientes WHERE id_cliente = ?");

        pstmt.setInt(1, idCliente);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             c = new Cliente();

             c.setIdCliente(rs.getInt("id_cliente"));
             c.setDataCadastro(rs.getTimestamp("data_cadastro"));
             c.setNome(rs.getString("nome"));
             c.setRg(rs.getString("rg"));
             c.setCpf(rs.getString("cpf"));
             c.setObservacoes(rs.getString("observacoes"));
         }

        rs.close();
        pstmt.close();

        return c;
    }


    public ClienteContadores getContadores(int idCliente) throws SQLException
    {
        ClienteContadores c = null;

        String sql =  "SELECT ";
               
               sql += " get_locacoes_pendentes_cliente(" + idCliente + ") AS total_locacoes_pendentes, ";
               sql += " get_locacoes_atrasadas_cliente(" + idCliente + ") AS total_locacoes_atrasadas, ";
               sql += " get_locacoes_devolvidas_cliente(" + idCliente + ") AS total_locacoes_devolvidas_no_prazo, ";

               sql += " get_receita_cliente(" + idCliente + ") AS receita_gerada, ";
               sql += " get_receita_estimada_cliente(" + idCliente + ") AS receita_estimada ";

        PreparedStatement pstmt = (PreparedStatement) conexao.con.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

         while ( rs.next() ) {
             c = new ClienteContadores();

             c.setTotalLocacoesAtradas(rs.getInt("total_locacoes_atrasadas"));
             c.setTotalLocacoesDevolvidasNoPrazo(rs.getInt("total_locacoes_devolvidas_no_prazo"));
             c.setTotalLocacoesPendentes(rs.getInt("total_locacoes_pendentes"));

             c.setReceitaGerada(rs.getDouble("receita_gerada"));
             c.setReceitaEstimada(rs.getDouble("receita_estimada"));
         }

        rs.close();
        pstmt.close();

        return c;
    }


    public List getLocacoes(int idCliente) throws SQLException
    {

        List<ClienteLocacoes> lista = new ArrayList<ClienteLocacoes>();
        
        PreparedStatement stmt = conexao.con.prepareStatement("SELECT * FROM locacoes_cliente WHERE id_cliente = ? ORDER BY id_locacao DESC");

        stmt.setInt(1, idCliente);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            ClienteLocacoes c = new ClienteLocacoes();

            c.setIdLocacao(rs.getInt("id_locacao"));
            c.setPromocao(rs.getString("promocao"));
            c.setQntFilmes(rs.getInt("qnt_filmes"));
            c.setValorLocacao(rs.getDouble("valor_locacao"));
            c.setDataLocacao(rs.getTimestamp("data_locacao"));

            lista.add(c);
        }

        stmt.close();

        return lista;
    }

}
