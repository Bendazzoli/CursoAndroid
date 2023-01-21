package com.benda.calculadoraprecocerveja.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    CervejaDAO dao = new CervejaDAO();
    EditText campoNome;
    EditText campoLitragem;
    EditText campoPreco;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cerveja);
        setTitle("Cadastrar Cerveja");

        campoNome = findViewById(R.id.activity_formulario_cerveja_nome);
        campoLitragem = findViewById(R.id.activity_formulario_cerveja_litragem);
        campoPreco = findViewById(R.id.activity_formulario_cerveja_preco);
        campoPreco.addTextChangedListener(new MonetaryTextWatcher(campoPreco));

        Intent cervejaRecebida = getIntent();
        cervejaParam = (Cerveja) cervejaRecebida.getSerializableExtra("cervejaParam");

        if(Objects.nonNull(cervejaParam)){
            campoNome.setText(cervejaParam.getNome());
            campoLitragem.setText(cervejaParam.getLitragem().toString());
            campoPreco.setText(cervejaParam.getPreco().toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_cerveja_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.activity_formulario_cerveja_menu_salvar){
            if (campoNome.getText().toString().isEmpty()) {
                Toast.makeText(this, "O nome da cerveja deve ser informado!", Toast.LENGTH_LONG).show();
            } else if (campoLitragem.getText().toString().isEmpty()) {
                Toast.makeText(this, "O volume da cerveja deve ser informado!", Toast.LENGTH_LONG).show();
            }else if (Integer.parseInt(campoLitragem.getText().toString()) == 0) {
                Toast.makeText(this, "O volume da cerveja não pode ser 0!", Toast.LENGTH_LONG).show();
            } else if (campoPreco.getText().toString().isEmpty()) {
                Toast.makeText(this, "O preço da cerveja deve ser informado!", Toast.LENGTH_LONG).show();
            } else if (campoPreco.getText().toString().equals("R$ 0,00")) {
                Toast.makeText(this, "O preço da cerveja não pode ser R$0.00!", Toast.LENGTH_LONG).show();
            } else {
                if (Objects.nonNull(cervejaParam)) {
                    editarCerveja();
                } else {
                    salvarCerveja();
                }
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarCerveja() {
        dao.salvar(new Cerveja(
                campoNome.getText().toString(),
                Integer.parseInt(campoLitragem.getText().toString()),
                Float.parseFloat(MonetaryHelper.cleanFormat(campoPreco.getText().toString()))
        ));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void editarCerveja() {
        Cerveja cervejaEditar = new Cerveja();
        cervejaEditar.setId(cervejaParam.getId());
        cervejaEditar.setNome(campoNome.getText().toString());
        cervejaEditar.setLitragem(Integer.parseInt(campoLitragem.getText().toString()));
        cervejaEditar.setPreco(Float.parseFloat(MonetaryHelper.cleanFormat(campoPreco.getText().toString())));
        dao.editar(cervejaEditar);
    }
}
