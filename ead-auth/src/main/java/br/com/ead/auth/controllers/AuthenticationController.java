package br.com.ead.auth.controllers;

import br.com.ead.auth.dtos.UsuarioDTO;
import br.com.ead.auth.enums.UsuarioSituacao;
import br.com.ead.auth.enums.UsuarioTipo;
import br.com.ead.auth.models.UsuarioModel;
import br.com.ead.auth.services.UsuarioService;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    UsuarioService userService;

    @PostMapping("registro")
    public ResponseEntity<Object> createUsuario(@RequestBody @Validated(UsuarioDTO.UserView.CadastroPost.class)
                                                @JsonView(UsuarioDTO.UserView.CadastroPost.class) UsuarioDTO userDTO) {
        log.debug("POST createUsuario userDTO recebido {}", userDTO.toString());

        if (this.userService.existsByUsuario(userDTO.getUsuario())) {
            log.warn("Erro: O usuário {} informado já existe!", userDTO.getUsuario());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O usuário informado já existe!");
        }

        if (this.userService.existsByEmail(userDTO.getEmail())) {
            log.warn("Erro: O email {} informado já existe!", userDTO.getEmail());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O email informado já existe!");
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(userDTO, usuarioModel);

        usuarioModel.setSituacao(UsuarioSituacao.ATIVO);
        usuarioModel.setTipo(UsuarioTipo.ALUNO);
        usuarioModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        usuarioModel.setDataUltimaAtualizacao((LocalDateTime.now(ZoneId.of("UTC"))));

        this.userService.saveUsuarioAndPublishRabbitMQ(usuarioModel);

        log.debug("POST createUsuario userDTO salvo {}", usuarioModel.getIdUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModel);
    }
}
