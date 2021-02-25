package com.benda.calculadoraprecocerveja.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.dao.CervejaDAO;
import com.benda.calculadoraprecocerveja.model.Cerveja;
import com.benda.calculadoraprecocerveja.ui.adapter.ListaCervejaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaCervejasActivity extends AppCompatActivity {
    private CervejaDAO dao = new CervejaDAO();
    private ListaCervejaAdapter listaCervejaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Cervejas");
        setContentView(R.layout.activity_lista_cervejas);

        configuraFABAddCerveja();
        configuraListaCerveja();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_cervejas_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cerveja cervejaEscolhida = listaCervejaAdapter.getItem(menuInfo.position);

        switch (item.getItemId()){
            case R.id.activity_lista_cervejas_menu_remover_item:
                confirmaRemocaoCerveja(cervejaEscolhida);
                break;
            case R.id.activity_lista_cervejas_menu_atualizar_item:
                editarCerveja(cervejaEscolhida);
                break;
        }
        onResume();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaCervejas();
    }

    private void atualizaCervejas(){
        listaCervejaAdapter.atualiza(dao.listarTodasCervejas());
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
        ListView listaDeCervejas = findViewById(R.id.activity_lista_de_cervejas_listview);
        listaCervejaAdapter = new ListaCervejaAdapter(ListaCervejasActivity.this);
        listaDeCervejas.setAdapter(listaCervejaAdapter);
        registerForContextMenu(listaDeCervejas);
    }

    private void confirmaRemocaoCerveja(final Cerveja cervejaEscolhida) {
        new AlertDialog
                .Builder(ListaCervejasActivity.this)
                .setMessage("Deseja realmente remover a cerveja selecionada?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removerCerveja(cervejaEscolhida);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void removerCerveja(Cerveja cerveja){
        dao.remover(cerveja);
        listaCervejaAdapter.remove(cerveja);
    }

    private void editarCerveja(Cerveja cerveja){
        Intent editarCerveja = new Intent(ListaCervejasActivity.this, FormularioCervejaActivity.class);
        editarCerveja.putExtra("cervejaParam", (Cerveja) cerveja);
        startActivity(editarCerveja);
    }
}
