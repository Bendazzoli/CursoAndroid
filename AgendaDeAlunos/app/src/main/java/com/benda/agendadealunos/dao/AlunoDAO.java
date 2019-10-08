package com.benda.agendadealunos.dao;

import com.benda.agendadealunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int sequenceId = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(sequenceId);
        alunos.add(aluno);
        sequenceId++;
    }

    public void editar(Aluno aluno){
        Aluno alunoEditado = null;
        for (Aluno a : alunos) {
            if(a.getId() == aluno.getId()){
                alunoEditado = a;
            }
        }

        if(alunoEditado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEditado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    public List<Aluno> getTodosAlunos() {
        return new ArrayList<>(alunos);
    }
}
