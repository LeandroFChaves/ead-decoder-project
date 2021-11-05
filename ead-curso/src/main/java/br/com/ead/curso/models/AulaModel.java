package br.com.ead.curso.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "AULAS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AulaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name="id_externo", nullable = false, updatable = false)
    private UUID idExterno;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 250)
    private String descricao;

    @Column(length = 250)
    private String videoUrl;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo", foreignKey = @ForeignKey(name = "FK_AULAS_MODULO"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ModuloModel modulo;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    public AulaModel() {
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ModuloModel getModulo() {
        return modulo;
    }

    public void setModulo(ModuloModel modulo) {
        this.modulo = modulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
