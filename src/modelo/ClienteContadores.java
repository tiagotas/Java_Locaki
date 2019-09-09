/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Tiago
 */
public class ClienteContadores {

    int totalLocacoesPendentes;
    int totalLocacoesAtradas;
    int totalLocacoesDevolvidasNoPrazo;

    double receitaGerada;
    double receitaEstimada;


    public double getReceitaEstimada() {
        return receitaEstimada;
    }

    public void setReceitaEstimada(double receitaEstimada) {
        this.receitaEstimada = receitaEstimada;
    }

    public double getReceitaGerada() {
        return receitaGerada;
    }

    public void setReceitaGerada(double receitaGerada) {
        this.receitaGerada = receitaGerada;
    }

    public int getTotalLocacoesAtradas() {
        return totalLocacoesAtradas;
    }

    public void setTotalLocacoesAtradas(int totalLocacoesAtradas) {
        this.totalLocacoesAtradas = totalLocacoesAtradas;
    }

    public int getTotalLocacoesDevolvidasNoPrazo() {
        return totalLocacoesDevolvidasNoPrazo;
    }

    public void setTotalLocacoesDevolvidasNoPrazo(int totalLocacoesDevolvidasNoPrazo) {
        this.totalLocacoesDevolvidasNoPrazo = totalLocacoesDevolvidasNoPrazo;
    }

    public int getTotalLocacoesPendentes() {
        return totalLocacoesPendentes;
    }

    public void setTotalLocacoesPendentes(int totalLocacoesPendentes) {
        this.totalLocacoesPendentes = totalLocacoesPendentes;
    }
}
