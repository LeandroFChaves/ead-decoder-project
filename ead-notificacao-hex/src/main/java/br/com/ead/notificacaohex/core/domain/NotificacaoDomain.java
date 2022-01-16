package br.com.ead.notificacaohex.core.domain;

import br.com.ead.notificacaohex.core.domain.enums.NotificacaoSituacao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class NotificacaoDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idNotificacao;
    private Long idUsuario;
    private UUID idExterno;
    private String titulo;
    private String mensagem;
    private NotificacaoSituacao notificacaoSituacao;
    private LocalDateTime dataCriacao;

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
