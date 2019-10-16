package com.benda.agendadealunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.benda.agendadealunos.R;
import com.benda.agendadealunos.dao.AlunoDAO;
import com.benda.agendadealunos.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.benda.agendadealunos.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity{

    private AlunoDAO dao = new AlunoDAO();
    private static final String TITULO_APPBAR = "Lista de Alunos";
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        ConfigurarFabNovoAluno();
        criarAlunosIniciais();
    }

    private void criarAlunosIniciais() {
        dao.salvar(new Aluno("Paulo", "1234567890", "paulo@gmail.com"));
        dao.salvar(new Aluno("Claudio", "1234567890", "claudio@gmail.com"));
        dao.salvar(new Aluno("Marcela", "1234567890", "marcela@gmail.com"));
    }

    private void ConfigurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormularioInsereAluno();
            }
        });
    }

    private void abrirFormularioInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarListaDeAlunos();
    }

    private void configurarListaDeAlunos() {
        final ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        final List<Aluno> alunos = dao.getTodosAlunos();
        configurarAdapter(listaAlunos, alunos);
        configurarListenerPorClickItem(listaAlunos);
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoDelecao  = (Aluno) adapterView.getItemAtPosition(posicao);
                dao.remover(alunoDelecao);
                adapter.remove(alunoDelecao);
                return true;
            }
        });
    }

    private void configurarListenerPorClickItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                abrirFomularioEdicaoAluno((Aluno) adapterView.getItemAtPosition(posicao));
            }
        });
    }

    private void abrirFomularioEdicaoAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configurarAdapter(ListView listaAlunos, List<Aluno> alunos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }
}