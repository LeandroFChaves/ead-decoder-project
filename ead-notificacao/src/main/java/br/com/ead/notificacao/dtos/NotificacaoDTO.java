package br.com.ead.notificacao.dtos;

import br.com.ead.notificacao.enums.NotificacaoSituacao;

import javax.validation.constraints.NotNull;

public class NotificacaoDTO {

    @NotNull
    private NotificacaoSituacao notificacaoSituacao;

    public NotificacaoSituacao getNotificacaoSituacao() {
        return notificacaoSituacao;
    }

    public void setNotificacaoSituacao(NotificacaoSituacao notificacaoSituacao) {
        this.notificacaoSituacao = notificacaoSituacao;
    }
}
