package br.com.ead.notificacaohex.core.ports;

import br.com.ead.notificacaohex.core.domain.NotificacaoDomain;
import br.com.ead.notificacaohex.core.domain.PageInfo;

import java.util.List;
import java.util.Optional;

public interface NotificacaoServicePort {

    List<NotificacaoDomain> findAllNotificacoesByUsuario(Long idUsuario, PageInfo pageable);

    Optional<NotificacaoDomain> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario);

    NotificacaoDomain salvarNotificacao(NotificacaoDomain notificacaoDomain);

}
