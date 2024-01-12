package org.anbruvic.dao;

import org.anbruvic.domain.Curso;

import java.util.List;

public interface ICursoDAO {

    public Curso cadastrar(Curso curso);

    void excluir(Curso cur);

    List<Curso> buscarTodos();
}
