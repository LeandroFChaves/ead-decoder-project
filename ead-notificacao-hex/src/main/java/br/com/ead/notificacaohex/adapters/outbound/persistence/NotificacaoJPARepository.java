package br.com.ead.notificacaohex.adapters.outbound.persistence;

import br.com.ead.notificacaohex.adapters.outbound.persistence.entities.NotificacaoEntity;
import br.com.ead.notificacaohex.core.domain.enums.NotificacaoSituacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificacaoJPARepository extends JpaRepository<NotificacaoEntity, Long> {

    Page<NotificacaoEntity> findAllByIdUsuarioAndNotificacaoSituacao(Long idUsuario,
                                                                    NotificacaoSituacao notificacaoSituacao,
                                                                    Pageable pageable);

    Optional<NotificacaoEntity> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario);

}
