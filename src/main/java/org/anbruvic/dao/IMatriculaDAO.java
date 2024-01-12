package org.anbruvic.dao;

import org.anbruvic.domain.Curso;
import org.anbruvic.domain.Matricula;

import java.util.List;

public interface IMatriculaDAO {
    Matricula cadastrar(Matricula mat);

    Matricula buscarPorCurso(Curso curso);

    Matricula buscarPorCodigoCurso(String codigoCurso);

    Matricula buscarPorCodigoCursoCriteria(String codigoCurso);

    Matricula buscarPorCursoCriteria(Curso curso);

    List<Matricula> buscarTodos();

    void excluir(Matricula matricula);
}
