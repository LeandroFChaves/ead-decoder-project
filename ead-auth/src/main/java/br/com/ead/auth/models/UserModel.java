package br.com.ead.auth.models;

import br.com.ead.auth.enums.UserSituacao;
import br.com.ead.auth.enums.UserTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "USUARIOS", uniqueConstraints = {
        @UniqueConstraint(name = "UK_USUARIOS_ID_EXTERNO", columnNames = {"id_externo"}),
        @UniqueConstraint(name = "UK_USUARIOS_USUARIO", columnNames = {"usuario"}),
        @UniqueConstraint(name = "UK_USUARIOS_EMAIL", columnNames = {"email"})}
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "id_externo", nullable = false, updatable = false)
    private UUID idExterno;

    @Column(nullable = false, length = 200)
    private String nomeCompleto;

    @Column(nullable = false, length = 50)
    private String usuario;

    @Column(nullable = false)
    @JsonIgnore
    private String senha;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 20)
    private String cpf;

    @Column
    private String imagemUrl;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserTipo tipo;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserSituacao situacao;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataUltimaAtualizacao;

    public UserModel() {
    }

    @PrePersist
    @SuppressWarnings("unused")
    private void initializeUUID() {
        if (idExterno == null) {
            idExterno = UUID.randomUUID();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public UserTipo getTipo() {
        return tipo;
    }

    public void setTipo(UserTipo tipo) {
        this.tipo = tipo;
    }

    public UserSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(UserSituacao situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", idExterno=" + idExterno +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", tipo=" + tipo +
                ", situacao=" + situacao +
                ", dataCriacao=" + dataCriacao +
                ", dataUltimaAtualizacao=" + dataUltimaAtualizacao +
                '}';
    }
}
