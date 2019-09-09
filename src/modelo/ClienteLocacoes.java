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
public class ClienteLocacoes
{

    private int idLocacao;
    private int idCliente;
    private int qntFilmes;

    private String promocao;

    private double valorLocacao;

    private Timestamp dataLocacao;



    public Timestamp getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Timestamp dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public int getQntFilmes() {
        return qntFilmes;
    }

    public void setQntFilmes(int qntFilmes) {
        this.qntFilmes = qntFilmes;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
}
