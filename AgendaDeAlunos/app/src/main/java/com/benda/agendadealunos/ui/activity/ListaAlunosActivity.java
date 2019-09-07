package com.benda.agendadealunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.benda.agendadealunos.R;
import com.benda.agendadealunos.dao.AlunoDAO;
import com.benda.agendadealunos.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    private AlunoDAO dao = new AlunoDAO();
    private static final String TITULO_APPBAR = "Lista de Alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        ConfigurarFabNovoAluno();
    }

    private void ConfigurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormularioAlunoActivity();
            }
        });
    }

    private void abrirFormularioAlunoActivity() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarListaDeAlunos();
    }

    private void configurarListaDeAlunos() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunos.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, dao.getTodosAlunos()));
    }
}