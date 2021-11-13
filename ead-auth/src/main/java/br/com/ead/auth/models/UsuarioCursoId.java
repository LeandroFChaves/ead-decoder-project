package br.com.ead.auth.models;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioCursoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsuario;
    private Long idCurso;

    public UsuarioCursoId() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioCursoId that = (UsuarioCursoId) o;
        return idUsuario.equals(that.idUsuario) && idCurso.equals(that.idCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idCurso);
    }
}
