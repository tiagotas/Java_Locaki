/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Timestamp;

/**
 *
 * @author Tiago
 */
public class Genero
{
    public int idGenero;
    public String descricao;
    public Timestamp dataCadastro;
    public String fgAtivo;

    
    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao.isEmpty())
            throw new RuntimeException("O campo descrição não pode estar vazio.");
        else
            this.descricao = descricao;
    }

    public String getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(String fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}
