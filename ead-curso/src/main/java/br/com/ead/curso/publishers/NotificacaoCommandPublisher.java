package br.com.ead.curso.publishers;

import br.com.ead.curso.dtos.NotificacaoCommandDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoCommandPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${ead.broker.exchange.notificacaoCommandExchange}")
    private String notificationCommandExchange;

    @Value(value = "${ead.broker.key.notificacaoCommandKey}")
    private String notificationCommandKey;

    public void publishNotificacaoCommand(NotificacaoCommandDTO notificacaoCommandDTO) {
        this.rabbitTemplate.convertAndSend(notificationCommandExchange, notificationCommandKey, notificacaoCommandDTO);
    }
}
