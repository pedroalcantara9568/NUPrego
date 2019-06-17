package com.example.appdaamiga;

import java.io.Serializable;

public class Aluno implements Serializable {

    private Integer id;
    private String nome;
    private String valor;
    private String descricao;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString(){
        return nome;
    }
}
