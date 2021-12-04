package br.com.ead.auth.controllers;

import br.com.ead.auth.dtos.DocenteDTO;
import br.com.ead.auth.enums.UserTipo;
import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/docentes")
public class DocenteController {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @PostMapping("/registro")
    public ResponseEntity<Object> saveRegistroDocente(@RequestBody @Valid DocenteDTO docenteDTO) {
        Optional<UserModel> userModelOptional = this.userService.findById(docenteDTO.getIdUsuario());

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } else {
            UserModel userModel = userModelOptional.get();
            userModel.setTipo(UserTipo.DOCENTE);
            userModel.setDataUltimaAtualizacao(LocalDateTime.now(ZoneId.of("UTC")));

            userService.updateUsuarioAndPublishRabbitMQ(userModel);

            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

}
