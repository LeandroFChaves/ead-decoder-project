package br.com.ead.notificacaohex.adapters.configs;

import br.com.ead.notificacaohex.EadNotificacaoHexApplication;
import br.com.ead.notificacaohex.core.ports.NotificacaoPersistencePort;
import br.com.ead.notificacaohex.core.services.NotificacaoServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = EadNotificacaoHexApplication.class)
public class BeanConfiguration {

    @Bean
    public NotificacaoServicePortImpl notificacaoServicePortImpl(NotificacaoPersistencePort persistence) {
        return new NotificacaoServicePortImpl(persistence);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
