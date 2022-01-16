package br.com.ead.notificacaohex.core.services;

import br.com.ead.notificacaohex.core.domain.NotificacaoDomain;
import br.com.ead.notificacaohex.core.domain.PageInfo;
import br.com.ead.notificacaohex.core.domain.enums.NotificacaoSituacao;
import br.com.ead.notificacaohex.core.ports.NotificacaoPersistencePort;
import br.com.ead.notificacaohex.core.ports.NotificacaoServicePort;

import java.util.List;
import java.util.Optional;

public class NotificacaoServicePortImpl implements NotificacaoServicePort {

    final NotificacaoPersistencePort notificacaoPersistencePort;

    public NotificacaoServicePortImpl(NotificacaoPersistencePort notificacaoPersistencePort) {
        this.notificacaoPersistencePort = notificacaoPersistencePort;
    }

    @Override
    public List<NotificacaoDomain> findAllNotificacoesByUsuario(Long idUsuario, PageInfo pageable) {
        return this.notificacaoPersistencePort.findAllByIdUsuarioAndNotificacaoSituacao(idUsuario, NotificacaoSituacao.CRIADO, pageable);
    }

    @Override
    public Optional<NotificacaoDomain> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario) {
        return this.notificacaoPersistencePort.findByIdNotificacaoAndIdUsuario(idNotificacao, idUsuario);
    }

    @Override
    public NotificacaoDomain salvarNotificacao(NotificacaoDomain notificacaoModel) {
        return this.notificacaoPersistencePort.save(notificacaoModel);
    }

}
