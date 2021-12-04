package br.com.ead.auth.publishers;

import br.com.ead.auth.dtos.UsuarioEventDTO;
import br.com.ead.auth.enums.TipoOperacao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEventPublisher {

    @Value(value = "${ead.broker.exchange.usuarioEvent}")
    private String exchangeUsuarioEvent;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void publishUsuarioEvent(UsuarioEventDTO usuarioEventDTO, TipoOperacao tipoOperacao) {
        usuarioEventDTO.setTipoOperacao(tipoOperacao.toString());
        rabbitTemplate.convertAndSend(exchangeUsuarioEvent, "", usuarioEventDTO);
    }

}
