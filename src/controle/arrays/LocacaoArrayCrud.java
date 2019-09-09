/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle.arrays;

import java.util.ArrayList;
import java.util.List;
import modelo.Filme;

/**
 *
 * @author Tiago
 */
public class LocacaoArrayCrud
{
    public static ArrayList<Filme> itens = new ArrayList<Filme>();

    public void add(Filme f)
    {
        if(itens.size() == 0)
            itens.add(f);
        else
        {
            for(int i=0; i < itens.size(); i++)
            {
                if(itens.get(i).getTitulo().equals(f.getTitulo()))
                    throw new RuntimeException("Este filme já foi adicionado a locação.");
                else
                {
                    itens.add(f);
                    break;
                }
            }
        }
    }


    public void remover(String filme)
    {
        for(int i=0; i < itens.size(); i++)
        {
            if(itens.get(i).getTitulo().equals(filme))
                itens.remove(i);
        }
    }

    
    public List listar()
    {
        return itens;
    }

    public void removeAll()
    {
        itens.removeAll(itens);
    }
}
