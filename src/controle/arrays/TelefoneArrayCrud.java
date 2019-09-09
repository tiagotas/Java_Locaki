/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle.arrays;

import java.util.ArrayList;
import java.util.List;
import modelo.Telefone;

/**
 *
 * @author Tiago
 */

public class TelefoneArrayCrud {

    public static ArrayList<Telefone> telefones = new ArrayList<Telefone>();

    public void add(Telefone t)
    {
        if(telefones.size() == 0)
            telefones.add(t);
        else
        {
            for(int i=0; i < telefones.size(); i++)
            {
                if(telefones.get(i).getNumero().equals(t.getNumero()))
                    throw new RuntimeException("Este telefone já foi adicionado a lista.");
                else
                {
                    telefones.add(t);
                    break;
                }
            }
        }
    }

    public void remover(String telefone)
    {
        for(int i=0; i < telefones.size(); i++)
        {
            if(telefones.get(i).getNumero().equals(telefone))
                telefones.remove(i);
        }
    }

    public Telefone getTelefone(String telefone)
    {
        Telefone t = new Telefone();

        for(int i=0; i < telefones.size(); i++)
        {
             if(telefones.get(i).getNumero().equals(telefone))
            {
                 t.setNumero(telefones.get(i).getNumero());
                 t.setTipoTelefone(telefones.get(i).getTipoTelefone());
                 t.setObservacoes(telefones.get(i).getObservacoes());
            }
        }

        return t;
    }

    public List listar()
    {
        return telefones;
    }
}