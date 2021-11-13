package br.com.ead.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(UsuarioCursoId.class)
@Table(name = "USUARIOS_CURSOS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioCursoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Id
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_USUARIOS_CURSOS_USUARIO"))
    private UserModel usuario;

    public UsuarioCursoModel() {
    }

    public UsuarioCursoModel(UserModel usuario, Long idUsuario, Long idCurso) {
        this.usuario = usuario;
        this.idUsuario = idUsuario;
        this.idCurso = idCurso;
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

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }
}
