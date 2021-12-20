package br.com.ead.auth.controllers;

import br.com.ead.auth.clients.CursoClient;
import br.com.ead.auth.models.UsuarioModel;
import br.com.ead.auth.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioCursoController {

    @Autowired
    CursoClient cursoClient;

    @Autowired
    UsuarioService userService;

    @GetMapping("/usuarios/{idUsuario}/cursos")
    public ResponseEntity<Object> getAllCursosByUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                        @PageableDefault(page = 0, size = 10, sort = "idCurso", direction = Sort.Direction.ASC) Pageable pageable,
                                                        @RequestHeader("Authorization") String token) {
        Optional<UsuarioModel> userModelOptional = this.userService.findById(idUsuario);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.cursoClient.getAllCursosByUsuario(idUsuario, token, pageable));
    }

}
