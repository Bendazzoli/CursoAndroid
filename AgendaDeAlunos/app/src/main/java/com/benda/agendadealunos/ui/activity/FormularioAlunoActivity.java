package com.benda.agendadealunos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.benda.agendadealunos.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle("Novo Aluno");
    }
}
