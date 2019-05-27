/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Date;

/**
 *
 * @author Tiago
 */
public class Genero
{
    public int idGenero;
    public String descricao;
    public Date dataCadastro;
    public String fgAtivo;

    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
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
