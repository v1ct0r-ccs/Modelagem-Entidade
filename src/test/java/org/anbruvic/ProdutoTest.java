package org.anbruvic;

import org.anbruvic.dao.*;
import org.anbruvic.domain.Curso;
import org.anbruvic.domain.Livro;
import org.anbruvic.domain.Produto;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProdutoTest {

    private IProdutoDAO produtoDAO;
    private ICursoDAO cursoDAO;
    private ILivroDAO livroDAO;

    public ProdutoTest() {
        produtoDAO = new ProdutoDAO();
        cursoDAO = new CursoDAO();
        livroDAO = new LivroDAO();
    }

    @After
    public void end() {
        List<Produto> produtoList = produtoDAO.buscarTodos();
        produtoList.forEach(prod -> produtoDAO.excluir(prod));

        List<Livro> livroList = livroDAO.buscarTodos();
        livroList.forEach(liv -> livroDAO.excluir(liv));

        List<Curso> cursoList = cursoDAO.buscarTodos();
        cursoList.forEach(cur -> cursoDAO.excluir(cur));
    }

    @Test
    public void cadastrar() {
        Curso curso = criarCurso("A1");
        List<Livro> livros = criarLivros("A1l");

        assertNotNull(curso);
        assertNotNull(curso.getId());
        assertNotNull(livros);
        assertEquals(2, livros.size());

        Produto prod = new Produto();
        prod.setCodigo("W23");
        prod.setNome("Pacote Java");
        prod.setDescricao("1 Curso e 2 Livros");

        prod.setCurso(curso);
        prod.setLivrosProduto(livros);
        prod = produtoDAO.cadastar(prod);

        assertNotNull(prod);
        assertNotNull(prod.getId());

        Produto produtoBD = produtoDAO.buscarPorNome(prod.getNome());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals("Pacote Java", produtoBD.getNome());

        double valorTotal = curso.getValor() + livros.stream().mapToDouble(Livro::getValor).sum();
        assertEquals(2124D, valorTotal, 0.0);

    }

    @Test
    public void atualizar() {
        Curso curso = criarCurso("A1");
        List<Livro> livros = criarLivros("A1l");

        assertNotNull(curso);
        assertNotNull(curso.getId());
        assertNotNull(livros);
        assertEquals(2, livros.size());

        Produto prod = new Produto();
        prod.setCodigo("W23");
        prod.setNome("Pacote Java");
        prod.setDescricao("1 Curso e 2 Livros");

        prod.setCurso(curso);
        prod.setLivrosProduto(livros);
        prod = produtoDAO.cadastar(prod);

        assertNotNull(prod);
        assertNotNull(prod.getId());

        Produto produtoBD = produtoDAO.buscarPorNome(prod.getNome());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals("Pacote Java", produtoBD.getNome());

        produtoBD.setNome("Pacote Java 2");
        produtoBD.setDescricao("1 Curso + Módulos e 2 Livros Revisados");

        produtoDAO.atualizar(produtoBD);

        Produto produtoBDAtt = produtoDAO.buscarPorNome("Pacote Java 2");
        assertNotNull(produtoBDAtt);
        assertEquals("Pacote Java 2", produtoBDAtt.getNome());
        assertEquals("1 Curso + Módulos e 2 Livros Revisados", produtoBDAtt.getDescricao());

        double valorTotal = curso.getValor() + livros.stream().mapToDouble(Livro::getValor).sum();
        assertEquals(2124D, valorTotal, 0.0);

    }

    private Curso criarCurso(String codigo) {
        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setDescricao("CURSO TESTE");
        curso.setNome("Curso de Java Backend");
        curso.setDisciplina("Java");
        curso.setValor(2000D);
        return cursoDAO.cadastrar(curso);
    }

    private List<Livro> criarLivros(String codigo) {
        Curso curso = criarCurso(codigo);
        List<Livro> livros = new ArrayList<>();
        Livro liv = new Livro();
        liv.setCodigo("154K");
        liv.setTitulo("Introdução Java");
        liv.setEdicao("Revisada 2022/3");
        liv.setDisciplina("Java");
        liv.setValor(45D);
        liv.setCurso(curso);
        livros.add(livroDAO.cadastar(liv));

        Livro liv2 = new Livro();
        liv2.setCodigo("998B");
        liv2.setTitulo("Introdução Java");
        liv2.setEdicao("Revisada 2023/2");
        liv2.setDisciplina("Java");
        liv2.setValor(79D);
        liv2.setCurso(curso);
        livros.add(livroDAO.cadastar(liv2));

        return livros;
    }
}
