package com.benda.calculadoraprecocerveja.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class ListaCervejaAdapter extends BaseAdapter {
    private final List<Cerveja> cervejas = new ArrayList<>();
    private Context context;

    public ListaCervejaAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return cervejas.size();
    }

    @Override
    public Cerveja getItem(int posicao) {
        return cervejas.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return cervejas.get(posicao).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_cerveja_custom_cell, viewGroup, false);

        Cerveja cervejaDevolvida = cervejas.get(posicao);

        TextView titulo = viewCriada.findViewById(R.id.item_cerveja_nome_litragem);
        titulo.setText(cervejaDevolvida.getNome() + " " + cervejaDevolvida.getLitragem() + "ml");

        TextView subTitulo = viewCriada.findViewById(R.id.item_cerveja_preco_por_litro);
        subTitulo.setText("R$" + cervejaDevolvida.calculaPrecoLitro() + " / litro");
        return viewCriada;
    }

    public void clear() {
        cervejas.clear();
    }

    public void addAll(List<Cerveja> cervejas) {
        this.cervejas.addAll(cervejas);
    }

    public void remove(Cerveja cerveja) {
        this.cervejas.remove(cerveja);
    }
}
