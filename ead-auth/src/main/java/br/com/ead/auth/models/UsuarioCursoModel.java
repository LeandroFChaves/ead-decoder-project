package br.com.ead.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(UsuarioCursoId.class)
@Table(name = "USUARIOS_CURSOS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioCursoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIOS_CURSOS_USUARIO"))
    private UserModel usuario;

    @Id
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

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
}
