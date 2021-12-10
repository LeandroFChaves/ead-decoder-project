package br.com.ead.notificacao.services.impl;

import br.com.ead.notificacao.repositories.NotificacaoRepository;
import br.com.ead.notificacao.services.NotificacaoService;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    final NotificacaoRepository notificacaoRepository;

    public NotificacaoServiceImpl(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }
}
