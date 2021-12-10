package br.com.ead.notificacao.models;

import br.com.ead.notificacao.enums.NotificacaoSituacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "NOTIFICACOES")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNotificacao;

    @Column(nullable = false)
    private Long idUsuario;

    @Type(type = "uuid-char")
    @Column(name = "id_externo", nullable = false, updatable = false)
    private UUID idExterno;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 250)
    private String mensagem;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificacaoSituacao notificacaoSituacao;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @PrePersist
    @SuppressWarnings("unused")
    private void initializeUUID() {
        if (idExterno == null) {
            idExterno = UUID.randomUUID();
        }
    }
    
    public Long getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Long idNotificacao) {
        this.idNotificacao = idNotificacao;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public NotificacaoSituacao getNotificacaoSituacao() {
        return notificacaoSituacao;
    }

    public void setNotificacaoSituacao(NotificacaoSituacao notificacaoSituacao) {
        this.notificacaoSituacao = notificacaoSituacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
