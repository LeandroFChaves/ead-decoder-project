package br.com.ead.curso.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CursoUsuarioId.class)
@Table(name = "CURSOS_USUARIOS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoUsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_curso", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CURSOS_USUARIOS_CURSO"))
    private CursoModel curso;

    public CursoUsuarioModel() {
    }

    public CursoUsuarioModel(CursoModel curso, Long idCurso, Long idUsuario) {
        this.curso = curso;
        this.idCurso = idCurso;
        this.idUsuario = idUsuario;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }
}
