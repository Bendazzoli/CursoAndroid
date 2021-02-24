package com.benda.calculadoraprecocerveja.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.dao.CervejaDAO;
import com.benda.calculadoraprecocerveja.databinding.ActivityListaCervejasBinding;
import com.benda.calculadoraprecocerveja.model.Cerveja;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaCervejasActivity extends AppCompatActivity {

    private ActivityListaCervejasBinding binding;
    private ArrayAdapter<Cerveja> cervejaAdapter;
    private CervejaDAO dao = new CervejaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Cervejas");
        binding = ActivityListaCervejasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configuraFABAddCerveja();
        configuraListaCerveja();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Cerveja cervejaEscolhida = cervejaAdapter.getItem(menuInfo.position);
        dao.remover(cervejaEscolhida);
        onResume();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaCervejas();
    }

    private void atualizaCervejas(){
        cervejaAdapter.clear();
        cervejaAdapter.addAll(dao.listarTodasCervejas());
    }

    private void configuraFABAddCerveja(){
        FloatingActionButton botaoNovaCerveja = findViewById(R.id.activity_lista_cervejas_fab_nova_cerveja);

        botaoNovaCerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaCervejasActivity.this, FormularioCervejaActivity.class));
            }
        });
    }

    private void configuraListaCerveja(){
        ListView listaDeCervejas = binding.activityListaDeCervejasListview;

        cervejaAdapter = new ArrayAdapter<>(ListaCervejasActivity.this, R.layout.lista_cerveja_custom_cell);
        listaDeCervejas.setAdapter(cervejaAdapter);

        configuraListenerClickPorItem(listaDeCervejas);
        registerForContextMenu(listaDeCervejas);
    }

    private void configuraListenerClickPorItem(ListView listaDeCervejasListView) {
        listaDeCervejasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Intent editarCerveja = new Intent(ListaCervejasActivity.this, FormularioCervejaActivity.class);

                editarCerveja.putExtra("cervejaParam", (Cerveja) adapterView.getItemAtPosition(posicao));
                startActivity(editarCerveja);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void removeCerveja(Cerveja cerveja){
        dao.remover(cerveja);
        cervejaAdapter.remove(cerveja);
    }
}
