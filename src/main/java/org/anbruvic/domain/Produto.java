package org.anbruvic.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "sq_curso", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "Nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToMany
    @JoinTable(name = "TB_PRODUTO_LIVRO",
            joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livrosProduto = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String combo) {
        this.nome = combo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Livro> getLivrosProduto() {
        return livrosProduto;
    }

    public void setLivrosProduto(List<Livro> livrosProduto) {
        this.livrosProduto = livrosProduto;
    }
}
