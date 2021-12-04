package br.com.ead.curso.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USUARIOS", uniqueConstraints = {
        @UniqueConstraint(name = "UK_USUARIOS_ID_EXTERNO", columnNames = {"id_externo"}),
        @UniqueConstraint(name = "UK_USUARIOS_EMAIL", columnNames = {"email"})}
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long idUsuario;

    @Type(type = "uuid-char")
    @Column(name = "id_externo", nullable = false, updatable = false)
    private UUID idExterno;

    @Column(nullable = false, length = 200)
    private String nomeCompleto;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 20)
    private String cpf;

    @Column
    private String imagemUrl;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CursoModel> cursos;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(nullable = false, length = 20)
    private String situacao;

    public UsuarioModel() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UUID getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(UUID idExterno) {
        this.idExterno = idExterno;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Set<CursoModel> getCursos() {
        return cursos;
    }

    public void setCursos(Set<CursoModel> cursos) {
        this.cursos = cursos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "idUsuario=" + idUsuario +
                ", idExterno=" + idExterno +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", tipo=" + tipo +
                ", situacao=" + situacao +
                '}';
    }
}
