package br.com.ead.notificacaohex.core.ports;

import br.com.ead.notificacaohex.core.domain.NotificacaoDomain;
import br.com.ead.notificacaohex.core.domain.PageInfo;
import br.com.ead.notificacaohex.core.domain.enums.NotificacaoSituacao;

import java.util.List;
import java.util.Optional;

public interface NotificacaoPersistencePort {
    NotificacaoDomain save(NotificacaoDomain notificacaoDomain);

    List<NotificacaoDomain> findAllByIdUsuarioAndNotificacaoSituacao(Long idUsuario,
                                                                     NotificacaoSituacao notificacaoSituacao,
                                                                     PageInfo pageInfo);

    Optional<NotificacaoDomain> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario);
}
