package br.com.ead.curso.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CursoUsuarioId.class)
@Table(name = "CURSOS_USUARIOS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoUsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", foreignKey = @ForeignKey(name = "FK_CURSOS_USUARIOS_CURSO"))
    private CursoModel curso;

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
