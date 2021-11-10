package br.com.ead.auth.controllers;

import br.com.ead.auth.dtos.UserDTO;
import br.com.ead.auth.enums.UserSituacao;
import br.com.ead.auth.enums.UserTipo;
import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.services.UserService;
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
    UserService userService;

    @PostMapping("registro")
    public ResponseEntity<Object> createUsuario(@RequestBody @Validated(UserDTO.UserView.CadastroPost.class)
                                                @JsonView(UserDTO.UserView.CadastroPost.class) UserDTO userDTO) {
        log.debug("POST createUsuario userDTO recebido {}", userDTO.toString());

        if (this.userService.existsByUsuario(userDTO.getUsuario())) {
            log.warn("Erro: O usuário {} informado já existe!", userDTO.getUsuario());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O usuário informado já existe!");
        }

        if (this.userService.existsByEmail(userDTO.getEmail())) {
            log.warn("Erro: O email {} informado já existe!", userDTO.getEmail());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: O email informado já existe!");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);

        userModel.setSituacao(UserSituacao.ATIVO);
        userModel.setTipo(UserTipo.ALUNO);
        userModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setDataUltimaAtualizacao((LocalDateTime.now(ZoneId.of("UTC"))));

        this.userService.save(userModel);

        log.debug("POST createUsuario userDTO salvo {}", userModel.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}
