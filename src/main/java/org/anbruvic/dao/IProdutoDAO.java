package org.anbruvic.dao;

import jdk.dynalink.linker.LinkerServices;
import org.anbruvic.domain.Produto;

import java.util.List;

public interface IProdutoDAO {
    Produto cadastar(Produto prod);


    Produto buscarPorNome(String nome);

    Produto atualizar(Produto produtoBD);
    
    List<Produto> buscarTodos();

    void excluir(Produto prod);
}
