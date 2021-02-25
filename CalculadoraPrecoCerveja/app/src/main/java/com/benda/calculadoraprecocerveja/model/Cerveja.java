package com.benda.calculadoraprecocerveja.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Cerveja implements Comparable<Cerveja>, Serializable {
    private int id = 0;
    private String nome;
    private Integer litragem;
    private Float preco;

    public Cerveja(String nome, Integer litragem, Float preco) {
        this.nome = nome;
        this.litragem = litragem;
        this.preco = preco;
    }

    public Cerveja() {}

    @NonNull
    @Override
    public String toString() {
        return this.nome + " " + this.litragem + "ml - (R$" + calculaPrecoLitro() + " / litro)" ;
    }

    public String calculaPrecoLitro() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        return df.format((this.preco * 1000) / this.litragem);
    }

    @Override
    public int compareTo(Cerveja cerveja) {
        return this.calculaPrecoLitro().compareTo(cerveja.calculaPrecoLitro());
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setLitragem(Integer litragem){
        this.litragem = litragem;
    }

    public Integer getLitragem(){
        return this.litragem;
    }

    public void setPreco(Float preco){
        this.preco = preco;
    }

    public Float getPreco(){
        return this.preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}