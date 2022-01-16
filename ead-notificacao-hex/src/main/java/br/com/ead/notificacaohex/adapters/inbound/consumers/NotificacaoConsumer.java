package br.com.ead.notificacaohex.adapters.inbound.consumers;

import br.com.ead.notificacaohex.adapters.dtos.NotificacaoCommandDTO;
import br.com.ead.notificacaohex.core.domain.NotificacaoDomain;
import br.com.ead.notificacaohex.core.domain.enums.NotificacaoSituacao;
import br.com.ead.notificacaohex.core.ports.NotificacaoServicePort;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class NotificacaoConsumer {

    final NotificacaoServicePort notificacaoServicePort;

    public NotificacaoConsumer(NotificacaoServicePort notificacaoService) {
        this.notificacaoServicePort = notificacaoService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.notificacaoCommandQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.notificacaoCommandExchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ead.broker.key.notificacaoCommandKey}")
    )
    public void listenUsuarioEvent(@Payload NotificacaoCommandDTO notificacaoCommandDTO) {
        NotificacaoDomain notificacaoDomain = new NotificacaoDomain();

        BeanUtils.copyProperties(notificacaoCommandDTO, notificacaoDomain);
        notificacaoDomain.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        notificacaoDomain.setNotificacaoSituacao(NotificacaoSituacao.CRIADO);

        this.notificacaoServicePort.salvarNotificacao(notificacaoDomain);
    }
}
