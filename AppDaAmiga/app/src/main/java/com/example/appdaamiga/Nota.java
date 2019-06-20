package com.example.appdaamiga;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Nota implements Serializable {

    private Integer id;
    private String nome;
    private Double valor;
    private String descricao;

    public void setId(Integer id) {
        this.id = id;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
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

    public Double getValor() {
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
