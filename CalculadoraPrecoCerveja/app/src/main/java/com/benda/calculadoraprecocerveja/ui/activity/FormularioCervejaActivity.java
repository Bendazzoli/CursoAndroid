package com.benda.calculadoraprecocerveja.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.dao.CervejaDAO;
import com.benda.calculadoraprecocerveja.model.Cerveja;

import java.math.BigDecimal;

public class FormularioCervejaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cerveja);
        setTitle("Cadastrar Cerveja");

        final CervejaDAO dao = new CervejaDAO();

        final EditText campoNome = findViewById(R.id.activity_formulario_cerveja_nome);
        final EditText campoLitragem = findViewById(R.id.activity_formulario_cerveja_litragem);
        final EditText campoPreco = findViewById(R.id.activity_formulario_cerveja_preco);

        Button botaoSalvar = findViewById(R.id.activity_formulario_cerveja_botao_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campoNome.getText().toString();
                Integer litragem = Integer.parseInt(campoLitragem.getText().toString());
                Float preco =  Float.parseFloat(campoPreco.getText().toString());

                Cerveja novaCerveja = new Cerveja(nome, litragem, preco);
                dao.salvar(novaCerveja);

                finish();
            }
        });
    }
}
