package com.benda.calculadoraprecocerveja.model;

import androidx.annotation.NonNull;

public class Cerveja {
    private final String nome;
    private final Integer litragem;
    private final Float preco;

    public Cerveja(String nome, Integer litragem, Float preco) {
        this.nome = nome;
        this.litragem = litragem;
        this.preco = preco;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome + " " + this.litragem + "ml - (R$" + calculaPrecoLitro() + " / litro)" ;
    }

    private Float calculaPrecoLitro() {
        return (this.preco * 1000) / this.litragem;
    }
}
