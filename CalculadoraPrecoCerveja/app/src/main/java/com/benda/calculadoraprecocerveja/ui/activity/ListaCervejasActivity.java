package com.benda.calculadoraprecocerveja.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.dao.CervejaDAO;
import com.benda.calculadoraprecocerveja.databinding.ActivityListaCervejasBinding;
import com.benda.calculadoraprecocerveja.model.Cerveja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaCervejasActivity extends AppCompatActivity {

    ActivityListaCervejasBinding binding;
    private ArrayAdapter<Cerveja> cervejaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Cervejas");
        binding = ActivityListaCervejasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        final CervejaDAO dao = new CervejaDAO();
        ListView listaDeCervejasListView = binding.activityListaDeCervejasListview;

        cervejaAdapter = new ArrayAdapter<>(ListaCervejasActivity.this, R.layout.lista_cerveja_custom_cell, dao.listarTodasCervejas());
        listaDeCervejasListView.setAdapter(cervejaAdapter);

        listaDeCervejasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Intent editarCerveja = new Intent(ListaCervejasActivity.this, FormularioCervejaActivity.class);

                editarCerveja.putExtra("cervejaParam", (Cerveja) adapterView.getItemAtPosition(posicao));
                startActivity(editarCerveja);
            }
        });

        listaDeCervejasListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Cerveja cervejaEscolhida = (Cerveja) adapterView.getItemAtPosition(posicao);
                dao.remover(cervejaEscolhida);
                cervejaAdapter.remove(cervejaEscolhida);
                return true;
            }
        });
    }
}
