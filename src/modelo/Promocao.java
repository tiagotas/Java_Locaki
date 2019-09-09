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
public class Promocao
{
    
    public int idPromocao;
    public String descricao;
    public double coeficienteDesconto;
    public Timestamp dataCadastro;
    public String fgAtivo;

    public double getCoeficienteDesconto() {
        return coeficienteDesconto;
    }

    public void setCoeficienteDesconto(double coeficienteDesconto) {
        if(String.valueOf(coeficienteDesconto).isEmpty())
            throw new RuntimeException("O coeficiente de desconto não pode estar vazio.");
        else
            this.coeficienteDesconto = coeficienteDesconto;
    }

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
             throw new RuntimeException("O descrição não pode estar vazio.");
        else
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
