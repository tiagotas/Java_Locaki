/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Tiago
 */


public class Locacao
{

    private int idLocacao;

    private String titulo, cliente, promocao;

    private ArrayList<Filme> itensLocacao;

    private Date dataDevolucaoEstimada;

    private Timestamp dataLocacao, dataDevolucaoReal;

    private double valorLocacao;


    public int getIdLocacao()
    {
        return idLocacao;
    }
    
    public void setIdLocacao(int idLocacao)
    {
        this.idLocacao = idLocacao;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getDataLocacao()
    {
        return dataLocacao;
    }

    public void setDataLocacao(Timestamp dataLocacao)
    {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucaoEstimada() {
        return dataDevolucaoEstimada;
    }

    public void setDataDevolucaoEstimada(Date dataDevolucaoEstimada) {
        this.dataDevolucaoEstimada = dataDevolucaoEstimada;
    }

    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(Timestamp dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public ArrayList<Filme> getItensLocacao() {
        return itensLocacao;
    }

    public void setItensLocacao(ArrayList<Filme> itensLocacao) {
        this.itensLocacao = itensLocacao;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
}



 