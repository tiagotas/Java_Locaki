/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Cliente {

    private int idCliente;

    private static ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
    private static ArrayList<Telefone> telefones = new ArrayList<Telefone>();

    private String nome;
    private String cpf;
    private String rg;
    private String fgAtivo;
    private String observacoes;

    private Timestamp dataCadastro;


    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf.isEmpty())
            throw new RuntimeException("O campo cpf não pode estar vazio.");
        else
            this.cpf = cpf;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public ArrayList getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List enderecos) {
        Cliente.enderecos = (ArrayList<Endereco>) enderecos;
    }
    
    public void setTelefones(List telefones) {
        Cliente.telefones = (ArrayList<Telefone>) telefones;
    }

    public String getFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(String fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isEmpty())
            throw new RuntimeException("O campo nome não pode estar vazio.");
        else
            this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        if(rg.isEmpty())
            throw new RuntimeException("O campo rg não pode estar vazio.");
        else
            this.rg = rg;
    }

    public ArrayList getTelefones() {
        return telefones;
    }
}