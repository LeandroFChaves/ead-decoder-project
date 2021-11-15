package br.com.ead.auth.dtos;

import javax.validation.constraints.NotNull;

public class DocenteDTO {

    @NotNull
    private Long idUsuario;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
