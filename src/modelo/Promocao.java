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
public class Promocao
{
    
    public int idPromocao;
    public String descricao;
    public double coeficienteDesconto;
    public Date dataCadastro;
    public String fgAtivo;

    public double getCoeficienteDesconto() {
        return coeficienteDesconto;
    }

    public void setCoeficienteDesconto(double coeficienteDesconto) {
        this.coeficienteDesconto = coeficienteDesconto;
    }

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

    public int getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }
}
