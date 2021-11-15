package br.com.ead.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EadCursoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EadCursoApplication.class, args);
    }

}
