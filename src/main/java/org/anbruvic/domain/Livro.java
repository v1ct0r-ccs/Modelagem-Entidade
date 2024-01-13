package org.anbruvic.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_LIVRO")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livr_seq")
    @SequenceGenerator(name = "livr_seq", sequenceName = "sq_livro", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "TITULO", length = 50, nullable = false)
    private String titulo;

    @Column(name = "EDICAO", length = 25, nullable = false)
    private String edicao;

    @Column(name = "DISCIPLINA", length = 30, nullable = false)
    private String disciplina;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_curso_livro_fk", foreignKey = @ForeignKey(name = "fk_curso_livro"), referencedColumnName = "id", nullable = false)
    private Curso curso;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
