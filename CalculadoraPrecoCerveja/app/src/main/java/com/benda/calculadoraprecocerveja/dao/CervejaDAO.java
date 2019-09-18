package com.benda.calculadoraprecocerveja.dao;

import com.benda.calculadoraprecocerveja.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class CervejaDAO {

    private static final List<Cerveja> cervejas = new ArrayList<>();

    public void salvar(Cerveja cerveja) {
        cervejas.add(cerveja);
    }

    public List<Cerveja> listarTodasCervejas() {
        return new ArrayList<>(cervejas);
    }
}
