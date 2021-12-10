package br.com.ead.notificacao.services.impl;

import br.com.ead.notificacao.enums.NotificacaoSituacao;
import br.com.ead.notificacao.models.NotificacaoModel;
import br.com.ead.notificacao.repositories.NotificacaoRepository;
import br.com.ead.notificacao.services.NotificacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    final NotificacaoRepository notificacaoRepository;

    public NotificacaoServiceImpl(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @Override
    public Page<NotificacaoModel> findAllNotificacoesByUsuario(Long idUsuario, Pageable pageable) {
        return this.notificacaoRepository.findAllByIdUsuarioAndNotificacaoSituacao(idUsuario, NotificacaoSituacao.CRIADO, pageable);
    }

    @Override
    public Optional<NotificacaoModel> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario) {
        return this.notificacaoRepository.findByIdNotificacaoAndIdUsuario(idNotificacao, idUsuario);
    }

    @Override
    public NotificacaoModel salvarNotificacao(NotificacaoModel notificacaoModel) {
        return this.notificacaoRepository.save(notificacaoModel);
    }

}
