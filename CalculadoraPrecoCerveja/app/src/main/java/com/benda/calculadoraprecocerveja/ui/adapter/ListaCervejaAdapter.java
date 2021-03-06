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
    private final Context context;

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
        return vincula(criaView(viewGroup), cervejas.get(posicao));
    }

    public void remove(Cerveja cerveja) {
        this.cervejas.remove(cerveja);
        notifyDataSetChanged();
    }

    public void atualiza(List<Cerveja> cervejas){
        this.cervejas.clear();
        this.cervejas.addAll(cervejas);
        notifyDataSetChanged();
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cerveja_custom_cell, viewGroup, false);
    }
    private View vincula(View view, Cerveja cerveja) {
        TextView titulo = view.findViewById(R.id.item_cerveja_nome_litragem);
        titulo.setText(cerveja.getNome() + " " + cerveja.getLitragem() + "ml");

        TextView subTitulo = view.findViewById(R.id.item_cerveja_preco_por_litro);
        subTitulo.setText("R$" + cerveja.calculaPrecoLitro() + " / litro");
        return view;
    }

}
