package org.anbruvic;

import org.anbruvic.dao.CursoDAO;
import org.anbruvic.dao.ICursoDAO;
import org.anbruvic.dao.ILivroDAO;
import org.anbruvic.dao.LivroDAO;
import org.anbruvic.domain.Curso;
import org.anbruvic.domain.Livro;
import org.junit.After;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class LivroTest {

    private ILivroDAO livroDAO;

    private ICursoDAO cursoDAO;

    public LivroTest() {
        livroDAO = new LivroDAO();
        cursoDAO = new CursoDAO();
    }

    @After
    public void end() {
        List<Livro> list = livroDAO.buscarTodos();
        list.forEach(liv -> livroDAO.excluir(liv));

        List<Curso> listCursos = cursoDAO.buscarTodos();
        listCursos.forEach(cur -> cursoDAO.excluir(cur));
    }

    @Test
    public void cadastrar() {
        Curso curso = criarCurso("A1");
        Livro liv = new Livro();
        liv.setCodigo("998B");
        liv.setTitulo("Introdução Java");
        liv.setEdicao("Revisada 2023/2");
        liv.setDisciplina("Java");
        liv.setValor(79D);
        liv.setCurso(curso);
        liv = livroDAO.cadastar(liv);

        assertNotNull(liv);
        assertNotNull(liv.getId());
    }

    @Test
    public void buscarPorCodigoLivro() {
        Curso curso = criarCurso("A1");
        Livro liv = new Livro();
        liv.setCodigo("154K");
        liv.setTitulo("Introdução Java");
        liv.setEdicao("Revisada 2022/3");
        liv.setDisciplina("Java");
        liv.setValor(45D);
        liv.setCurso(curso);
        liv = livroDAO.cadastar(liv);

        assertNotNull(liv);
        assertNotNull(liv.getId());

        Livro livroBD = livroDAO.buscarPorCodigoLivro(liv.getCodigo());
        assertNotNull(livroBD);
        assertEquals(liv.getId(), livroBD.getId());
    }

    @Test
    public void ListaLivrosRecomendados() {
        Curso curso = criarCurso("A1");

        Livro liv = new Livro();
        liv.setCodigo("154K");
        liv.setTitulo("Introdução Java");
        liv.setEdicao("Revisada 2022/3");
        liv.setDisciplina("Java");
        liv.setValor(45D);
        liv.setCurso(curso);
        liv = livroDAO.cadastar(liv);

        Livro liv2 = new Livro();
        liv2.setCodigo("998B");
        liv2.setTitulo("Introdução Java");
        liv2.setEdicao("Revisada 2023/2");
        liv2.setDisciplina("Java");
        liv2.setValor(79D);
        liv2.setCurso(curso);
        liv2 = livroDAO.cadastar(liv2);

        assertNotNull(liv);
        assertNotNull(liv2);
        assertNotNull(liv.getId());
        assertNotNull(liv2.getId());


        List<Livro> livroBD = livroDAO.listaLivrosRecomendados(curso);
        assertEquals(2, livroBD.size());

        Livro livroBD1 = livroDAO.buscarPorCodigoLivro(liv.getCodigo());
        assertEquals(liv.getId(), livroBD1.getId());

        Livro livroBD2 = livroDAO.buscarPorCodigoLivro(liv2.getCodigo());
        assertEquals(liv2.getId(), livroBD2.getId());
    }

    @Test
    public void alterar() {
        Curso curso = criarCurso("A1");
        Livro liv = new Livro();
        liv.setCodigo("998B");
        liv.setTitulo("Introdução Java");
        liv.setEdicao("Revisada 2023/2");
        liv.setDisciplina("Java");
        liv.setValor(79D);
        liv.setCurso(curso);
        liv = livroDAO.cadastar(liv);

        assertNotNull(liv);
        assertNotNull(liv.getId());

        Livro livroBD = livroDAO.buscarPorCodigoLivro(liv.getCodigo());
        assertNotNull(livroBD);
        assertEquals(liv.getId(), livroBD.getId());

        livroBD.setEdicao("Revisado 2024/1");
        livroBD.setValor(120D);

        Livro livroBDAtt = livroDAO.atualizar(livroBD);
        assertEquals("Revisado 2024/1", livroBDAtt.getEdicao());
        assertEquals(120d, livroBDAtt.getValor(), 0.0D);
    }


    private Curso criarCurso(String codigo) {
        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setDescricao("CURSO TESTE");
        curso.setNome("Curso de Java Backend");
        return cursoDAO.cadastrar(curso);
    }
}
