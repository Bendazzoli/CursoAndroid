package com.benda.agendadealunos.dao;

import com.benda.agendadealunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> getTodosAlunos() {
        return new ArrayList<>(alunos);
    }
}
