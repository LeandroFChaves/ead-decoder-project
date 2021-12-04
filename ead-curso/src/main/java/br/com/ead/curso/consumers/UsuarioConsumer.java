package br.com.ead.curso.consumers;

import br.com.ead.curso.dtos.UsuarioEventDTO;
import br.com.ead.curso.enums.TipoOperacao;
import br.com.ead.curso.models.UsuarioModel;
import br.com.ead.curso.services.UsuarioService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConsumer {

    @Autowired
    UsuarioService usuarioService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.usuarioEventQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.usuarioEventExchange}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true"))
    )
    public void listenUsuarioEvent(@Payload UsuarioEventDTO usuarioEventDTO) {
        UsuarioModel userModel = usuarioEventDTO.convertToUsuarioModel();

        switch (TipoOperacao.valueOf(usuarioEventDTO.getTipoOperacao())) {
            case CREATE:
            case UPDATE:
                this.usuarioService.save(userModel);
                break;
            case DELETE:
                this.usuarioService.delete(userModel.getIdUsuario());
                break;
        }
    }
}
