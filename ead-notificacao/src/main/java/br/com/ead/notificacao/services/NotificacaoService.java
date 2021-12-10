package br.com.ead.notificacao.services;

import br.com.ead.notificacao.models.NotificacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotificacaoService {

    Page<NotificacaoModel> findAllNotificacoesByUsuario(Long idUsuario, Pageable pageable);

    Optional<NotificacaoModel> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario);

    NotificacaoModel salvarNotificacao(NotificacaoModel notificacaoModel);

}
