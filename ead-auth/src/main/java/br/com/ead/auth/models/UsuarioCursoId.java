package br.com.ead.auth.models;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioCursoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserModel usuario;
    private Long idCurso;

    public UsuarioCursoId(UserModel usuario, Long idCurso) {
        this.usuario = usuario;
        this.idCurso = idCurso;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
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
        return usuario.equals(that.usuario) && idCurso.equals(that.idCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, idCurso);
    }
}
