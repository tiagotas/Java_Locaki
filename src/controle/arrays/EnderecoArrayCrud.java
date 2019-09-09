/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle.arrays;

import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;

/**
 *
 * @author Tiago
 */
public class EnderecoArrayCrud {

    public static ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

    public void add(Endereco e)
    {
        if(enderecos.size() == 0)
             enderecos.add(e);
        else
        {
            for(int i=0; i < enderecos.size(); i++)
            {
                if(e.getLagradouro().equals(enderecos.get(i).getLagradouro()))
                    throw new RuntimeException("Este endereço já foi adicionado a lista.");
                else
                {
                    enderecos.add(e);
                    break;
                }
            }
        }
    }

    public void remover(String endereco)
    {
        for(int i=0; i < enderecos.size(); i++)
        {
            if(endereco.equals(enderecos.get(i).getLagradouro() + ", " + enderecos.get(i).getNumero()))
                enderecos.remove(i);
        }
    }

    public Endereco getEndereco(String endereco)
    {
        Endereco e = new Endereco();

        for(int i=0; i < enderecos.size(); i++)
        {
            String stringEndereco = enderecos.get(i).getLagradouro() + ", " + enderecos.get(i).getNumero();       

            if(stringEndereco.equals(endereco))
            {
                e.setLagradouro(enderecos.get(i).getLagradouro());
                e.setNumero(enderecos.get(i).getNumero());
                e.setBairro(enderecos.get(i).getBairro());
                e.setCep(enderecos.get(i).getCep());
                e.setCidade(enderecos.get(i).getCidade());
                e.setEstado(enderecos.get(i).getEstado());
            }
        }

        return e;
    }

    public List listar()
    {        
        return enderecos;
    }
}