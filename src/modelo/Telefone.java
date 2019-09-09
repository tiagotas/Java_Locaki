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
public class Telefone
{

    private int idTelefone;
    private int idCliente;
    private String tipoTelefone;
    private String numero;
    private String observacoes;
    private String ativo;
    private Timestamp dataCadastro;


    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(numero.isEmpty())
            throw new RuntimeException("O campo número não pode estar vazio.");
        else
            this.numero = numero;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        if(tipoTelefone.isEmpty())
            throw new RuntimeException("Você deve selecionar o tipo de telefone.");
        else
            this.tipoTelefone = tipoTelefone;
    }    
}


