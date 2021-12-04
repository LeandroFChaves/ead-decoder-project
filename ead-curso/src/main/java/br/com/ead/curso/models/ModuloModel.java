package br.com.ead.curso.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "MODULOS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuloModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idModulo;

    @Type(type = "uuid-char")
    @Column(name="id_externo", nullable = false, updatable = false)
    private UUID idExterno;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 250)
    private String descricao;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", foreignKey = @ForeignKey(name = "FK_MODULOS_CURSO"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CursoModel curso;

    @OneToMany(mappedBy = "modulo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<AulaModel> aulas;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    public ModuloModel() {
    }

    @PrePersist
    @SuppressWarnings("unused")
    private void initializeUUID() {
        if (idExterno == null) {
            idExterno = UUID.randomUUID();
        }
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public UUID getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(UUID idExterno) {
        this.idExterno = idExterno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }

    public Set<AulaModel> getAulas() {
        return aulas;
    }

    public void setAulas(Set<AulaModel> aulas) {
        this.aulas = aulas;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "ModuloModel{" +
                "id=" + idModulo +
                ", idExterno=" + idExterno +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", curso=" + curso +
                ", aulas=" + aulas +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
