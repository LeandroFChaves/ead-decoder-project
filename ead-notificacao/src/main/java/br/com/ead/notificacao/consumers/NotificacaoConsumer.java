package br.com.ead.notificacao.consumers;

import br.com.ead.notificacao.dtos.NotificacaoCommandDTO;
import br.com.ead.notificacao.enums.NotificacaoSituacao;
import br.com.ead.notificacao.models.NotificacaoModel;
import br.com.ead.notificacao.services.NotificacaoService;
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

    final NotificacaoService notificacaoService;

    public NotificacaoConsumer(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.notificacaoCommandQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.notificacaoCommandExchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ead.broker.key.notificacaoCommandKey}")
    )
    public void listenUsuarioEvent(@Payload NotificacaoCommandDTO notificacaoCommandDTO) {
        NotificacaoModel notificacaoModel = new NotificacaoModel();

        BeanUtils.copyProperties(notificacaoCommandDTO, notificacaoModel);
        notificacaoModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        notificacaoModel.setNotificacaoSituacao(NotificacaoSituacao.CRIADO);

        this.notificacaoService.salvarNotificacao(notificacaoModel);
    }
}
