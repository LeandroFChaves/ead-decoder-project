package br.com.ead.notificacao.repositories;

import br.com.ead.notificacao.enums.NotificacaoSituacao;
import br.com.ead.notificacao.models.NotificacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoModel, Long> {

    Page<NotificacaoModel> findAllByIdUsuarioAndNotificacaoSituacao(Long idUsuario,
                                                                    NotificacaoSituacao notificacaoSituacao,
                                                                    Pageable pageable);

    Optional<NotificacaoModel> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario);

}
