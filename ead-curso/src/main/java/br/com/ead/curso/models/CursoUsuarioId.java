package br.com.ead.curso.models;

import java.io.Serializable;
import java.util.Objects;

public class CursoUsuarioId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idCurso;
    private Long idUsuario;

    public CursoUsuarioId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoUsuarioId that = (CursoUsuarioId) o;
        return idCurso.equals(that.idCurso) && idUsuario.equals(that.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, idUsuario);
    }
}
