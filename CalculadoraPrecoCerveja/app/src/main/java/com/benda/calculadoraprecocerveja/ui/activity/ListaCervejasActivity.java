package com.benda.calculadoraprecocerveja.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.dao.CervejaDAO;
import com.benda.calculadoraprecocerveja.model.Cerveja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaCervejasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Cervejas");
        setContentView(R.layout.activity_lista_cervejas);

        FloatingActionButton botaoNovaCerveja = findViewById(R.id.activity_lista_cervejas_fab_nova_cerveja);

        botaoNovaCerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaCervejasActivity.this, FormularioCervejaActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CervejaDAO dao = new CervejaDAO();
        ListView listaDeCervejas = findViewById(R.id.activity_lista_de_cervejas_listview);
        listaDeCervejas.setAdapter(new ArrayAdapter<Cerveja>(this, android.R.layout.simple_list_item_1, dao.listarTodasCervejas()));
    }
}
