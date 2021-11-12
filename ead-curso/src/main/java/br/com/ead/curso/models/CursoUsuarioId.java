package br.com.ead.curso.models;

import java.io.Serializable;
import java.util.Objects;

public class CursoUsuarioId implements Serializable {
    private static final long serialVersionUID = 1L;

    private CursoModel curso;
    private Long idUsuario;

    public CursoUsuarioId(CursoModel curso, Long idUsuario) {
        this.curso = curso;
        this.idUsuario = idUsuario;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoUsuarioId that = (CursoUsuarioId) o;
        return curso.equals(that.curso) && idUsuario.equals(that.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, idUsuario);
    }
}
