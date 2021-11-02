package br.com.ead.auth.controllers;

import br.com.ead.auth.models.UserModel;
import br.com.ead.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
        Optional<UserModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } else {
            this.userService.delete(userModelOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Usuário apagado com sucesso");
        }
    }
}
