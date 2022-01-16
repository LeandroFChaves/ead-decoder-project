package br.com.ead.notificacaohex.adapters.outbound.persistence;

import br.com.ead.notificacaohex.adapters.outbound.persistence.entities.NotificacaoEntity;
import br.com.ead.notificacaohex.core.domain.NotificacaoDomain;
import br.com.ead.notificacaohex.core.domain.PageInfo;
import br.com.ead.notificacaohex.core.domain.enums.NotificacaoSituacao;
import br.com.ead.notificacaohex.core.ports.NotificacaoPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NotificacaoPersistencePortImpl implements NotificacaoPersistencePort {

    private final NotificacaoJPARepository notificacaoJPARepository;
    private final ModelMapper modelMapper;

    public NotificacaoPersistencePortImpl(NotificacaoJPARepository notificacaoJPARepository, ModelMapper modelMapper) {
        this.notificacaoJPARepository = notificacaoJPARepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NotificacaoDomain save(NotificacaoDomain notificacaoDomain) {
        NotificacaoEntity notificacaoEntity = this.notificacaoJPARepository.save(modelMapper.map(notificacaoDomain, NotificacaoEntity.class));

        return this.modelMapper.map(notificacaoEntity, NotificacaoDomain.class);
    }

    @Override
    public List<NotificacaoDomain> findAllByIdUsuarioAndNotificacaoSituacao(Long idUsuario,
                                                                            NotificacaoSituacao notificacaoSituacao,
                                                                            PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());

        return this.notificacaoJPARepository.findAllByIdUsuarioAndNotificacaoSituacao(idUsuario, notificacaoSituacao, pageable).stream().map(entity -> modelMapper.map(entity, NotificacaoDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificacaoDomain> findByIdNotificacaoAndIdUsuario(Long idNotificacao, Long idUsuario) {
        Optional<NotificacaoEntity> notificationEntityOptional = this.notificacaoJPARepository.findByIdNotificacaoAndIdUsuario(idNotificacao, idUsuario);

        if (notificationEntityOptional.isPresent()) {
            return Optional.of(modelMapper.map(notificationEntityOptional.get(), NotificacaoDomain.class));
        } else {
            return Optional.empty();
        }
    }
}
