package br.com.ead.notificacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EadNotificacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EadNotificacaoApplication.class, args);
    }

}
