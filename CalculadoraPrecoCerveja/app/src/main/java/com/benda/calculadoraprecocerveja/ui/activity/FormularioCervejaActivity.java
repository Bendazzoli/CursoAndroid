package com.benda.calculadoraprecocerveja.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.benda.calculadoraprecocerveja.R;
import com.benda.calculadoraprecocerveja.dao.CervejaDAO;
import com.benda.calculadoraprecocerveja.internal.MonetaryHelper;
import com.benda.calculadoraprecocerveja.internal.MonetaryTextWatcher;
import com.benda.calculadoraprecocerveja.model.Cerveja;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class FormularioCervejaActivity extends AppCompatActivity {

    Cerveja cervejaParam;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cerveja);
        setTitle("Cadastrar Cerveja");

        final CervejaDAO dao = new CervejaDAO();

        final EditText campoNome = findViewById(R.id.activity_formulario_cerveja_nome);
        final EditText campoLitragem = findViewById(R.id.activity_formulario_cerveja_litragem);
        final EditText campoPreco = findViewById(R.id.activity_formulario_cerveja_preco);

        findViewById(R.id.activity_formulario_cerveja_botao_salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.nonNull(cervejaParam)){
                    Cerveja cervejaEditar = new Cerveja();
                    cervejaEditar.setId(cervejaParam.getId());
                    cervejaEditar.setNome(campoNome.getText().toString());
                    cervejaEditar.setLitragem(Integer.parseInt(campoLitragem.getText().toString()));
                    cervejaEditar.setPreco(Float.parseFloat(MonetaryHelper.cleanFormat(campoPreco.getText().toString())));
                    dao.editar(cervejaEditar);
                }else{
                    dao.salvar(new Cerveja(
                            campoNome.getText().toString(),
                            Integer.parseInt(campoLitragem.getText().toString()),
                            Float.parseFloat(MonetaryHelper.cleanFormat(campoPreco.getText().toString()))
                    ));
                }

                finish();
            }
        });

        campoPreco.addTextChangedListener(new MonetaryTextWatcher(campoPreco));

        Intent cervejaRecebida = getIntent();
        cervejaParam = (Cerveja) cervejaRecebida.getSerializableExtra("cervejaParam");

        if(Objects.nonNull(cervejaParam)){
            campoNome.setText(cervejaParam.getNome());
            campoLitragem.setText(cervejaParam.getLitragem().toString());
            campoPreco.setText(cervejaParam.getPreco().toString());
        }
    }
}
