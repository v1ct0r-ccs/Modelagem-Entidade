package org.anbruvic.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.anbruvic.domain.Curso;
import org.anbruvic.domain.Livro;

import java.util.List;

public class LivroDAO implements ILivroDAO {
    @Override
    public Livro cadastar(Livro liv) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(liv);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return liv;
    }

    @Override
    public Livro buscarPorCodigoLivro(String codigoLivro) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT l FROM Livro l ");
        sb.append("WHERE l.codigo = :codigoLivro ");

        entityManager.getTransaction().begin();
        TypedQuery<Livro> query = entityManager.createQuery(sb.toString(), Livro.class);
        query.setParameter("codigoLivro", codigoLivro);
        Livro livro = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return livro;
    }

    @Override
    public List<Livro> listaLivrosRecomendados(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Livro> query = builder.createQuery(Livro.class);
        Root<Livro> root = query.from(Livro.class);
        query.where(builder.equal(root.get("curso"), curso));
        query.select(root);

        TypedQuery<Livro> tpQuery = entityManager.createQuery(query);
        List<Livro> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public Livro atualizar(Livro livroBD) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        livroBD = entityManager.merge(livroBD);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return livroBD;
    }

    @Override
    public List<Livro> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Livro> query = builder.createQuery(Livro.class);
        Root<Livro> root = query.from(Livro.class);
        query.select(root);

        TypedQuery<Livro> tpQuery = entityManager.createQuery(query);
        List<Livro> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public void excluir(Livro liv) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        liv = entityManager.merge(liv);
        entityManager.remove(liv);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

}