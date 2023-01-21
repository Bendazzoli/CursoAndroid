package com.benda.calculadoraprecocerveja.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.benda.calculadoraprecocerveja.model.Cerveja;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CervejaDAO {

    private static List<Cerveja> cervejas = new ArrayList<>();
    private static int incrementadorId = 1;

    public void salvar(Cerveja cerveja) {
        cerveja.setId(incrementadorId);
        cervejas.add(cerveja);
        incrementadorId++;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void editar(Cerveja cerveja){
        for (Cerveja c: cervejas) {
            if(Objects.equals(c.getId(), cerveja.getId())){
                cervejas.set(cervejas.indexOf(c), cerveja);
                break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void remover(Cerveja cerveja){
        for (Cerveja c: cervejas) {
            if(Objects.equals(c.getId(), cerveja.getId())){
                cervejas.remove(cerveja);
                break;
            }
        }
    }

    public List<Cerveja> listarTodasCervejas() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cervejas.sort(Comparator.comparing(Cerveja::calculaPrecoLitro));
        }
        return cervejas;
    }
}
