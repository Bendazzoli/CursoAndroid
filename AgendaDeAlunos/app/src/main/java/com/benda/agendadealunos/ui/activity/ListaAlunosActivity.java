package com.benda.agendadealunos.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.benda.agendadealunos.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle("Lista de Alunos");
        List<String> alunos = new ArrayList<>(Arrays.asList("Huguinho","Zezinho","Luizinho", "Pedrinho", "Claudinho", "Buchecha", "Romário"));
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos));
    }
}