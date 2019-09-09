/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Timestamp;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tiago
 */
public class Filme 
{
    private int idFilme, unidades, diasDevolucao;
    private double valor;
    private String titulo, fgAtivo, lancamento;
    private Timestamp dataCadastro;

    static private DefaultListModel generos;

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getDiasDevolucao() {
        return diasDevolucao;
    }

    public void setDiasDevolucao(int diasDevolucao) {
        this.diasDevolucao = diasDevolucao;
    }

    public String getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(String fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public static DefaultListModel getGeneros() {
        return generos;
    }

    public static void setGeneros(DefaultListModel generos) {
        Filme.generos = generos;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}