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
public class Endereco
{

    private int idEndereco;
    private int idCliente;

    private String lagradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private Timestamp dataCadastro;
    private String ativo;


    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if(bairro.isEmpty())
            throw new RuntimeException("O campo bairro não pode estar vazio.");
        else
            this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if(cep.isEmpty())
            throw new RuntimeException("O campo cep não pode estar vazio.");
        else
            this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if(cidade.isEmpty())
            throw new RuntimeException("O campo cidade não pode estar vazio.");
        else
            this.cidade = cidade;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if(estado.isEmpty())
            throw new RuntimeException("Você deve selecionar um estado.");
        else
            this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLagradouro() {
        return lagradouro;
    }

    public void setLagradouro(String lagradouro) {
        if(lagradouro.isEmpty())
            throw new RuntimeException("O campo lagradouro não pode estar vazio.");
        else
            this.lagradouro = lagradouro;
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
}
