package br.com.ead.notificacao.repositories;

import br.com.ead.notificacao.models.NotificacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoModel, Long> {
}
