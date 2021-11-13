package br.com.ead.auth.dtos;

import javax.validation.constraints.NotNull;

public class MatriculaUsuarioDTO {

    private Long idUsuario;

    @NotNull
    private Long idCurso;

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
}
