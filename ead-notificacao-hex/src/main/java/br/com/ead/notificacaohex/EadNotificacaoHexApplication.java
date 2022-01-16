package br.com.ead.notificacaohex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EadNotificacaoHexApplication {

    public static void main(String[] args) {
        SpringApplication.run(EadNotificacaoHexApplication.class, args);
    }

}
