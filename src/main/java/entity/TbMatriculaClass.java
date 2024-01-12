package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tb_matricula", schema = "public", catalog = "JPA")
public class TbMatriculaClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "codigo")
    private String codigo;
    @Basic
    @Column(name = "data_matricula")
    private Timestamp dataMatricula;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "valor")
    private double valor;
    @Basic
    @Column(name = "id_curso_fk")
    private long idCursoFk;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Timestamp dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getIdCursoFk() {
        return idCursoFk;
    }

    public void setIdCursoFk(long idCursoFk) {
        this.idCursoFk = idCursoFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbMatriculaClass that = (TbMatriculaClass) o;

        if (id != that.id) return false;
        if (Double.compare(valor, that.valor) != 0) return false;
        if (idCursoFk != that.idCursoFk) return false;
        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) return false;
        if (dataMatricula != null ? !dataMatricula.equals(that.dataMatricula) : that.dataMatricula != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        result = 31 * result + (dataMatricula != null ? dataMatricula.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (idCursoFk ^ (idCursoFk >>> 32));
        return result;
    }


}
