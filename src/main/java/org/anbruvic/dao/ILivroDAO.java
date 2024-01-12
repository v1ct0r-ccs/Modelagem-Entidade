package org.anbruvic.dao;

import org.anbruvic.domain.Curso;
import org.anbruvic.domain.Livro;

import java.util.List;

public interface ILivroDAO {
    Livro cadastar(Livro liv);

    Livro buscarPorCodigoLivro(String codigoLivro);

    List<Livro> listaLivrosRecomendados(Curso curso);

    Livro atualizar(Livro livroBD);

    List<Livro> buscarTodos();

    void excluir(Livro liv);
}
