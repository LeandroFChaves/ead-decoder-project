package br.com.ead.curso.models;

import br.com.ead.curso.enums.CursoNivel;
import br.com.ead.curso.enums.CursoSituacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CURSOS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "id_externo", nullable = false, updatable = false)
    private UUID idExterno;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 250)
    private String descricao;

    @Column
    private String imagemUrl;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private CursoSituacao situacao;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private CursoNivel nivel;

    @Column(nullable = false)
    private Long usuarioDocente;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<ModuloModel> modulos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CURSOS_USUARIOS",
            joinColumns = @JoinColumn(name = "id_curso", foreignKey = @ForeignKey(name = "FK_CURSOS_USUARIOS_ID_CURSO")),
            inverseJoinColumns = @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_CURSOS_USUARIOS_ID_USUARIO")))
    private Set<UsuarioModel> usuarios;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataUltimaAtualizacao;

    public CursoModel() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public CursoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(CursoSituacao situacao) {
        this.situacao = situacao;
    }

    public CursoNivel getNivel() {
        return nivel;
    }

    public void setNivel(CursoNivel nivel) {
        this.nivel = nivel;
    }

    public Long getUsuarioDocente() {
        return usuarioDocente;
    }

    public void setUsuarioDocente(Long usuarioDocente) {
        this.usuarioDocente = usuarioDocente;
    }

    public Set<ModuloModel> getModulos() {
        return modulos;
    }

    public void setModulos(Set<ModuloModel> modulos) {
        this.modulos = modulos;
    }

    public Set<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
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
        return "CursoModel{" +
                "id=" + id +
                ", idExterno=" + idExterno +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", situacao=" + situacao +
                ", nivel=" + nivel +
                ", usuarioDocente=" + usuarioDocente +
                ", modulos=" + modulos +
                ", dataCriacao=" + dataCriacao +
                ", dataUltimaAtualizacao=" + dataUltimaAtualizacao +
                '}';
    }
}
