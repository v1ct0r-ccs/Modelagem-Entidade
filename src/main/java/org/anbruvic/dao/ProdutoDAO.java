package org.anbruvic.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.anbruvic.domain.Produto;

import java.util.List;

public class ProdutoDAO implements IProdutoDAO{

    @Override
    public Produto cadastar(Produto prod) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(prod);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return prod;
    }

    @Override
    public Produto buscarPorNome(String nome) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p FROM Produto p ");
        sb.append("WHERE p.nome = :nome");

        entityManager.getTransaction().begin();
        TypedQuery<Produto> query = entityManager.createQuery(sb.toString(), Produto.class);
        query.setParameter("nome", nome);
        Produto produto = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return produto;
    }

    @Override
    public Produto atualizar(Produto produtoBD) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        produtoBD = entityManager.merge(produtoBD);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return produtoBD;
    }

    @Override
    public List<Produto> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> root = query.from(Produto.class);
        query.select(root);

        TypedQuery<Produto> tpQuery = entityManager.createQuery(query);
        List<Produto> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public void excluir(Produto prod) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        prod = entityManager.merge(prod);
        entityManager.remove(prod);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }


}
